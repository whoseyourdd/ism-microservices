package com.whoseyourdd.ism.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whoseyourdd.ism.user.model.User;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}

