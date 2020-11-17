package com.cs322.ors.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.DishKeyWord;

@Repository
public interface DishKeyWordRepository extends JpaRepository <DishKeyWord, Long>{
	List<DishKeyWord> findByDish_Id(long dish_id);
	List<DishKeyWord> findByKeyWord(String keyWord);
	
}
