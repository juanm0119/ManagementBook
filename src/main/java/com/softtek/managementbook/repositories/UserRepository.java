package com.softtek.managementbook.repositories;

import com.softtek.managementbook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmailAndPassword(String email, String password);
  Optional<User> findByEmail(String email);
}