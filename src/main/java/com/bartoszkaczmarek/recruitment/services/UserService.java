package com.bartoszkaczmarek.recruitment.services;

import com.bartoszkaczmarek.recruitment.domain.UserDataDto;
import com.bartoszkaczmarek.recruitment.domain.UserDataResponse;
import org.springframework.stereotype.Service;

import static com.bartoszkaczmarek.recruitment.converter.UserDataConverter.asGithubUser;

@Service
public class UserService {

    private final GithubRestClient githubRestClient;
    private final LoginRequestCountService loginRequestCountService;

    UserService(GithubRestClient githubRestClient, LoginRequestCountService loginRequestCountService) {
        this.githubRestClient = githubRestClient;
        this.loginRequestCountService = loginRequestCountService;
    }

    public UserDataResponse getUser(String login) {
        loginRequestCountService.countLogin(login);
        UserDataDto githubUser = githubRestClient.getGithubUser(login);
        UserDataResponse userDataResponse = asGithubUser(githubUser);
        userDataResponse.setCalculations(resolveCalculation(githubUser));
        return userDataResponse;
    }

    private float resolveCalculation(UserDataDto githubUserDataDto) {
        float followers = githubUserDataDto.getFollowers();
        float publicReposCalculation = 2 + githubUserDataDto.getPublicRepos();
        float rightSideOfCalculation = followers * publicReposCalculation;

        if (rightSideOfCalculation != 0) {
            return 6 / followers * publicReposCalculation;
        } else {
            return 0;
        }
    }
}
