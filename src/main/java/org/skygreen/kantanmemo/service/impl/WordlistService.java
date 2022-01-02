package org.skygreen.kantanmemo.service.impl;

import org.apache.camel.ProducerTemplate;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.entity.Wordlist;
import org.skygreen.kantanmemo.service.IWordlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class WordlistService implements IWordlistService {
    public static final Logger LOG =
            LoggerFactory.getLogger(WordlistService.class);
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    WordDao wordDao;
    @Inject
    WordlistDao wordlistDao;

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
}
