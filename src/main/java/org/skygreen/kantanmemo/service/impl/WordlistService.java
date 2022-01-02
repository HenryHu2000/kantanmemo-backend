package org.skygreen.kantanmemo.service.impl;

import org.apache.camel.ProducerTemplate;
import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.entity.Wordlist;
import org.skygreen.kantanmemo.service.IWordlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Long uploadCsv(String filename, String body) {
        var words = producerTemplate.requestBody("direct:csv-to-json", body, List.class);
        wordDao.saveAll(words);

        var wordlist = new Wordlist();
        wordlist.setName(filename);
        wordlist.setWords(words);
        wordlistDao.save(wordlist);
        return wordlist.getId();
    }

    @Override
    public Map<String, Long> getAllWordlists() {
        var result = new HashMap<String, Long>();
        for (var wordlist : wordlistDao.findAll()) {
            result.put(wordlist.getName(), wordlist.getId());
        }
        return result;
    }

    @Override
    public Long userSelectWordlist(Long userId, Long wordlistId) {
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
            return wordlistId;
        } else {
            return null;
        }
    }
}
