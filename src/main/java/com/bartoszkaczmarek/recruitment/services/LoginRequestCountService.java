package com.bartoszkaczmarek.recruitment.services;

import com.bartoszkaczmarek.recruitment.domain.LoginRequestCount;
import com.bartoszkaczmarek.recruitment.repositories.LoginRequestCountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginRequestCountService {

    private final LoginRequestCountRepository repository;

    public LoginRequestCountService(LoginRequestCountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void countLogin(String login) {
        repository.findByLogin(login)
                .ifPresentOrElse(loginRequestCount -> {
                            loginRequestCount.setRequestCount(loginRequestCount.getRequestCount() + 1);
                            repository.save(loginRequestCount);
                        },
                        () -> repository.save(new LoginRequestCount(login, 1)));
    }
}
