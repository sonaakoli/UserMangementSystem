package com.shonali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shonali.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
