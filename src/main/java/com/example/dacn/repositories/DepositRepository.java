package com.example.dacn.repositories;

import com.example.dacn.entities.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {
    @Query(value = "select ifnull(sum(amount), 0) from deposits inner join users on users.id = deposits.user_id where username = ?1 and year(date) = ?2", nativeQuery = true)
    Double getDepositsInYear(@Param("username") String username, @Param("year") int year);

    @Query(value = "select sum(amount) from deposits inner join users on users.id = deposits.user_id where username = ?1 and year(date) = ?2 and month(date) = ?3", nativeQuery = true)
    Double getDepositsInMonth(@Param("username") String username, @Param("year") int year, @Param("month") int month);

    @Query(value = "select sum(amount) from deposits inner join users on users.id = deposits.user_id where username = ?1 and year(date) = ?2 and month(date) = ?3 and day(date) = ?4", nativeQuery = true)
    Double getDepositsOnDay(@Param("username") String username, @Param("year") int year, @Param("month") int month, @Param("day") int day);

    @Query(value = "select * from deposits inner join users on users.id = deposits.user_id where username = ?1", nativeQuery = true)
    List<Deposit> findAllByUsername(@Param("username") String username);

    @Query(value = "select ifnull(sum(amount), 0) from deposits inner join users on users.id = deposits.user_id where username = ?1", nativeQuery = true)
    Double getSumAllDepositOfCurrentUser(@Param("username") String username);

    @Query(value = "select ifnull(sum(amount), 0) from deposits inner join users on users.id = deposits.user_id where username = ?1 and year(date) = year(now()) and month(date) = month(now())", nativeQuery = true)
    Double getCurrentMonthDepositAmount(@Param("username") String username);

    @Query(value = "select * from deposits inner join users on users.id = deposits.user_id where username = ?1 and year(date) = year(now()) and month(date) = month(now()) order by amount desc limit 2", nativeQuery = true)
    List<Deposit> getTopDepositsOfThisMonth(@Param("username") String username);
}
