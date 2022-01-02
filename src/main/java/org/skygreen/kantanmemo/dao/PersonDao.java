package org.skygreen.kantanmemo.dao;

import org.skygreen.kantanmemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {
}