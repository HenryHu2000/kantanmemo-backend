package org.skygreen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyEntityDao extends JpaRepository<MyEntity, Long> {
    @Override
    Optional<MyEntity> findById(Long aLong);
}