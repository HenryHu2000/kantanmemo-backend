package org.skygreen.kantanmemo.service.impl;

import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dao.WordLearningDataDao;
import org.skygreen.kantanmemo.data.LearningProcess;
import org.skygreen.kantanmemo.dto.DailyProgressDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.entity.WordLearningData;
import org.skygreen.kantanmemo.service.ILearningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import java.util.*;

@ApplicationScoped
public class LearningService implements ILearningService {
    public static final Logger LOG = LoggerFactory.getLogger(LearningService.class);

    @Inject
    PersonDao personDao;
    @Inject
    WordLearningDataDao wordLearningDataDao;
    @Inject
    Map<Long, LearningProcess> learningProcessMap;


    public synchronized WordLearningData getCurrentWord(Long userId) {
        var person = verifyAndGetPerson(userId);
        var learningProcess = getLearningProcess(person);
        if (learningProcess.isTerminated()) {
            return null;
        }
        return learningProcess.getCurrentWord();
    }

    public synchronized WordLearningData proceedToNextWord(Long userId, boolean isKnown) {
        var person = verifyAndGetPerson(userId);
        var learningProcess = getLearningProcess(person);
        if (learningProcess.isTerminated()) {
            return null;
        }
        var currentWord = learningProcess.getCurrentWord();
        learningProcess.proceed(isKnown);
        wordLearningDataDao.save(currentWord);
        return learningProcess.getCurrentWord();
    }

    public synchronized DailyProgressDto getDailyProgress(Long userId) {
        var person = verifyAndGetPerson(userId);
        var learningProcess = getLearningProcess(person);
        return learningProcess.getDailyProgress();
    }

    public synchronized boolean resetLearningProcess(Long userId) {
        verifyAndGetPerson(userId);
        if (learningProcessMap.containsKey(userId)) {
            learningProcessMap.remove(userId);
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean addNewToLearningProcess(Long userId) {
        var person = verifyAndGetPerson(userId);
        var learningProcess = getLearningProcess(person);
        return learningProcess.addNewWordsToRemaining(generateUserLearningList(person, true, false));
    }

    private Person verifyAndGetPerson(Long userId) {
        if (userId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isEmpty()) {
            throw new ForbiddenException();
        }
        return personOpt.get();
    }

    private LearningProcess getLearningProcess(Person person) {
        var userId = person.getId();
        if (!learningProcessMap.containsKey(userId)) {
            learningProcessMap.put(userId, new LearningProcess(generateUserLearningList(person)));
        }
        return learningProcessMap.get(userId);
    }

    private List<WordLearningData> generateUserLearningList(Person person) {
        return generateUserLearningList(person, true, true);
    }

    private List<WordLearningData> generateUserLearningList(Person person, boolean includesNewWords, boolean includesRevisingWords) {
        if (person.getUserSettings() == null) {
            throw new ForbiddenException();
        }
        var settings = person.getUserSettings();

        var wordsToLearn = new ArrayList<WordLearningData>();
        if (includesRevisingWords) {
            // Add revising words
            var learnedWords = person.getWordLearningDataList();
            learnedWords.sort(Comparator
                    .comparing(WordLearningData::getFamiliarity)
                    .thenComparing(WordLearningData::getLastSeen));
            wordsToLearn.addAll(learnedWords.subList(0, Math.min(settings.getDailyRevisingWordNum(), learnedWords.size())));
        }
        if (includesNewWords) {
            // Add new words
            var currentWordlist = settings.getCurrentWordlist();
            if (currentWordlist != null) {
                var total = currentWordlist.getWords().size();
                var offset = person.getProgress().getOrDefault(currentWordlist, 0);
                var limit = Math.min(settings.getDailyNewWordNum(), total - offset);

                var newWords = currentWordlist.getWords().subList(offset, offset + limit);
                var newWordsToLearn = new ArrayList<WordLearningData>();
                for (var word : newWords) {
                    var wordLearningData = new WordLearningData();
                    wordLearningData.setWord(word);
                    wordLearningData.setFamiliarity(0);
                    wordLearningData.setLastSeen(Calendar.getInstance());

                    wordLearningDataDao.save(wordLearningData);
                    person.getWordLearningDataList().add(wordLearningData);
                    newWordsToLearn.add(wordLearningData);
                }
                Collections.shuffle(newWordsToLearn);
                wordsToLearn.addAll(newWordsToLearn);
                person.getProgress().put(currentWordlist, offset + limit);
            }
        }
        personDao.save(person);

        return wordsToLearn;
    }

}
