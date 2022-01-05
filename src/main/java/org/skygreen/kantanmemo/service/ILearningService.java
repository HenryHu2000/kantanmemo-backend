package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.entity.WordLearningData;

import java.util.List;

public interface ILearningService {
    WordLearningData getCurrentWord(Long userId);

    WordLearningData proceedToNextWord(Long userId, boolean isKnown);
}
