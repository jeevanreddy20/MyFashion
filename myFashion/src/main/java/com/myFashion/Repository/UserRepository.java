package com.myFashion.Repository;

import com.myFashion.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM payment_db.users where email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);




}
