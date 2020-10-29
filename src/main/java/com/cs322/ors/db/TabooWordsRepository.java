package com.cs322.ors.db;

import com.cs322.ors.model.TabooWords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TabooWordsRepository extends JpaRepository<TabooWords, Long> {
}
