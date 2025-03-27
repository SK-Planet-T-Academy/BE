package com.asac7_hackathon.hackathon.domain.users.repository;

import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  // 이메일을 기준으로 사용자 검색
  Optional<User> findByUserEmail(String userEmail);
}