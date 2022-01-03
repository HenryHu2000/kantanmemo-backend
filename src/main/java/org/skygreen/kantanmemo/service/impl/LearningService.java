package org.skygreen.kantanmemo.service.impl;

import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.data.LearningProcess;
import org.skygreen.kantanmemo.service.ILearningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class LearningService implements ILearningService {
    public static final Logger LOG = LoggerFactory.getLogger(LearningService.class);

    @Inject
    WordDao wordDao;
    @Inject
    WordlistDao wordlistDao;
    @Inject
    PersonDao personDao;
    @Inject
    Map<Long, LearningProcess> learningProcessMap;
}
