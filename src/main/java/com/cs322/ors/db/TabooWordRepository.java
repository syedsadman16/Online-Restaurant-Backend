package com.cs322.ors.db;

import com.cs322.ors.model.TabooWord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TabooWordRepository extends JpaRepository<TabooWord, Long> {
}
