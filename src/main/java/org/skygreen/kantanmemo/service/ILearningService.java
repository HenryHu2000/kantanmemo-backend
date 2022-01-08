package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.DailyProgressDTO;
import org.skygreen.kantanmemo.entity.WordLearningData;

public interface ILearningService {
    WordLearningData getCurrentWord(Long userId);

    WordLearningData proceedToNextWord(Long userId, boolean isKnown);

    DailyProgressDTO getDailyProgress(Long userId);

    boolean resetLearningProcess(Long userId);
}
