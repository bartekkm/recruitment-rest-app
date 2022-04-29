package com.bartoszkaczmarek.recruitment.services;

import com.bartoszkaczmarek.recruitment.domain.UserDataResponse;
import com.bartoszkaczmarek.recruitment.domain.UserDataDto;
import org.springframework.stereotype.Service;

import static com.bartoszkaczmarek.recruitment.converter.UserDataConverter.asGithubUser;

@Service
public class UserService {

    private final GithubRestClient githubRestClient;
    private final LoginRequestCountService loginRequestCountService;

    UserService(GithubRestClient githubRestClient, LoginRequestCountService loginRequestCountService) {
        this.githubRestClient = githubRestClient;
        this.loginRequestCountService = loginRequestCountService;
    };

    public UserDataResponse getUser(String login) {
        loginRequestCountService.countLogin(login);
        UserDataDto githubUser = githubRestClient.getGithubUser(login);
        UserDataResponse userDataResponse = asGithubUser(githubUser);
        userDataResponse.setCalculations(resolveCalculation(githubUser));
        return userDataResponse;
    }

    private float resolveCalculation(UserDataDto githubUserDataDto) {
        return 6 / githubUserDataDto.getFollowers() * (2 + githubUserDataDto.getPublicRepos());
    }
}
