package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.DailyProgressDto;
import org.skygreen.kantanmemo.entity.WordLearningData;

public interface ILearningService {
    WordLearningData getCurrentWord(Long userId);

    WordLearningData proceedToNextWord(Long userId, boolean isKnown);

    DailyProgressDto getDailyProgress(Long userId);

    boolean resetLearningProcess(Long userId);

    boolean addNewToLearningProcess(Long userId);
}
