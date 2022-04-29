package com.bartoszkaczmarek.recruitment.repositories;

import com.bartoszkaczmarek.recruitment.domain.LoginRequestCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRequestCountRepository extends JpaRepository<LoginRequestCount, Long> {

    Optional<LoginRequestCount> findByLogin(String login);
}
