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
import java.util.Comparator;
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
        return wordlistDao.findAll().stream()
                .map(wordlistMapper::wordlistToWordlistDto)
                .sorted(Comparator.comparing(WordlistDto::getName))
                .collect(Collectors.toList());
    }
}
