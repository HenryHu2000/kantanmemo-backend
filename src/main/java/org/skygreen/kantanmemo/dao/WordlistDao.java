package org.skygreen.kantanmemo.dao;

import org.skygreen.kantanmemo.entity.Wordlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordlistDao extends JpaRepository<Wordlist, Long> {
}