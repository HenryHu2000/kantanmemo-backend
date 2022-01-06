package org.skygreen.kantanmemo.dao;

import org.skygreen.kantanmemo.entity.WordLearningData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordLearningDataDao extends JpaRepository<WordLearningData, Long> {
}