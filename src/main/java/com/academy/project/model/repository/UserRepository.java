package com.academy.project.model.repository;

import com.academy.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User u " +
            "set u.password= :#{#user.password} " +
            "where u.id = :#{#user.id}")
    void updatePassword(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("update User u " +
            "set u.username= :#{#user.username}, " +
            "u.name= :#{#user.name}, " +
            "u.surname= :#{#user.surname}, " +
            "u.email= :#{#user.email}, " +
            "u.phoneNumber= :#{#user.phoneNumber} " +
            "where u.id = :#{#user.id}")
    void updateInfo(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("update User u " +
            "set u.accountNonLocked= :#{#user.accountNonLocked} " +
            "where u.id= :#{#user.id}")
    void updateAccessStatus(@Param("user") User user);

    boolean existsUserByUsername(String username);
}

