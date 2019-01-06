package com.karthikbose.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthikbose.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
