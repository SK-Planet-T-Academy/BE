package com.asac7_hackathon.hackathon.domain.users.repository;

import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User save(User entity);
  List<User> findAll();
}
