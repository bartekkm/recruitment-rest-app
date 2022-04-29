package com.bartoszkaczmarek.recruitment.services;

import com.bartoszkaczmarek.recruitment.config.GithubRestProperties;
import com.bartoszkaczmarek.recruitment.domain.UserDataDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubRestClient {

    private final RestTemplate restTemplate;
    private final GithubRestProperties restProperties;

    public GithubRestClient(RestTemplate restTemplate, GithubRestProperties restProperties) {
        this.restTemplate = restTemplate;
        this.restProperties = restProperties;
    }

    public UserDataDto getGithubUser(String login) {
        return restTemplate.getForObject(restProperties.getUserUrl() + login, UserDataDto.class);
    }
}
