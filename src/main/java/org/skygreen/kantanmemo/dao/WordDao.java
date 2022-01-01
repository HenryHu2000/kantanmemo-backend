package org.skygreen.kantanmemo.dao;

import org.skygreen.kantanmemo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordDao extends JpaRepository<Word, Long> {
}
