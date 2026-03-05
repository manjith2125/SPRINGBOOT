package com.tejait.batch15.repository;

import com.tejait.batch15.model.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TranscationRepository extends JpaRepository<Transcation, Integer> {

    List<Transcation> findAllByAppId(Integer appId);

    @Modifying
    @Transactional
    void deleteAllByAppId(Integer appId);

    // 🔥 ADD THIS METHOD
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE transcation AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}