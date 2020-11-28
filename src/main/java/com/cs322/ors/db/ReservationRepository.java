package com.cs322.ors.db;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.Order;
import com.cs322.ors.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select count(r) from Reservation r where r.table.id = :tableId" +
            " and not (" +
            " not (r.timeSlot.from >= :inputFrom and r.timeSlot.from < :inputTo)" +
            " and " +
            " not (r.timeSlot.to > :inputFrom and r.timeSlot.to <= :inputTo)" +
            " )"
    )
    

    long countOfConflictedReservations( Long tableId,
                                  LocalDateTime inputFrom,
                                  LocalDateTime inputTo);

    List<Reservation> findByTableId(Long tableId);
    
    List<Reservation> findByCustomerId(Long id);

}
