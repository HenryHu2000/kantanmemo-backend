package org.skygreen.kantanmemo.service.impl;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.dto.WordlistDto;
import org.skygreen.kantanmemo.entity.Word;
import org.skygreen.kantanmemo.entity.Wordlist;
import org.skygreen.kantanmemo.service.IWordlistService;
import org.skygreen.kantanmemo.service.mapper.WordlistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class WordlistService implements IWordlistService {
    public static final Logger LOG = LoggerFactory.getLogger(WordlistService.class);
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    WordDao wordDao;
    @Inject
    WordlistDao wordlistDao;
    @Inject
    PersonDao personDao;

    @Inject
    WordlistMapper wordlistMapper;

    @Override
    public Long uploadCsv(String filename, String body) {
        if (filename == null || body == null) {
            throw new ForbiddenException();
        }
        List<Word> words;
        try {
            words = producerTemplate.requestBody("direct:csv-to-json", body, List.class);
        } catch (CamelExecutionException e) {
            throw new ForbiddenException();
        }
        wordDao.saveAll(words);

        var wordlist = new Wordlist();
        wordlist.setName(filename);
        wordlist.setWords(words);
        wordlistDao.save(wordlist);
        return wordlist.getId();
    }

    @Override
    public List<WordlistDto> getAllWordlists() {
        return wordlistDao.findAll().stream().map(wordlistMapper::wordlistToWordlistDto).collect(Collectors.toList());
    }

    @Override
    public WordlistDto selectUserWordlist(Long userId, Long wordlistId) {
        if (userId == null || wordlistId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        var wordlistOpt = wordlistDao.findById(wordlistId);
        if (personOpt.isPresent() && wordlistOpt.isPresent()) {
            var person = personOpt.get();
            var wordlist = wordlistOpt.get();
            person.setCurrentWordlist(wordlist);
            if (!person.getProgress().containsKey(wordlist)) {
                person.getProgress().put(wordlist, 0);
            }
            personDao.save(person);
            return wordlistMapper.wordlistToWordlistDto(wordlist);
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    public WordlistDto currentUserWordlist(Long userId) {
        if (userId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            var result = wordlistMapper.wordlistToWordlistDto(person.getCurrentWordlist());
            if (result == null) {
                return new WordlistDto(-1L, "");
            }
            return result;
        } else {
            throw new ForbiddenException();
        }
    }

}
