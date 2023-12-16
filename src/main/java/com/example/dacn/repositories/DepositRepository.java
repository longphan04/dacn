package com.example.dacn.repositories;

import com.example.dacn.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {
   @Query(value = "select sum(amount) from deposit where user_id = ?1 and year(date) = ?2 and month(date) = ?3 and day(date) = ?4", nativeQuery = true)
    Double getDepositsInYear(@Param("userId") int userId, @Param("year") int year, @Param("month") int month, @Param("day") int day);
}
