package com.cs322.ors.db;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cs322.ors.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
   
	//
    @Query("select rr from RestaurantTable rr where rr.id NOT in (select r.table.id from Reservation r where " +
            "not (" +
            " not (r.timeSlot.from >= :inputFrom and r.timeSlot.from < :inputTo)" +
            " and " +
            " not (r.timeSlot.to > :inputFrom and r.timeSlot.to <= :inputTo)" +
            " ))"
    )
    List<RestaurantTable> findAvailableTables(
                                  LocalDateTime inputFrom,
                                  LocalDateTime inputTo);


}