package com.example.dacn.repositories;

import com.example.dacn.entities.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {
    @Query(value = "select sum(amount) from withdraw inner join users on users.id = withdraw.user_id where username = ?1 and year(date) = ?2 and month(date) = ?3 and day(date) = ?4", nativeQuery = true)
    Double getWithdrawsInYear(@Param("username") String username, @Param("year") int year, @Param("month") int month, @Param("day") int day);
}
