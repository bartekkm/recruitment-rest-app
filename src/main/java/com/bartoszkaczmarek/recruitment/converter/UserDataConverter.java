package com.bartoszkaczmarek.recruitment.converter;

import com.bartoszkaczmarek.recruitment.domain.UserDataResponse;
import com.bartoszkaczmarek.recruitment.domain.UserDataDto;

public class UserDataConverter {

    public static UserDataResponse asGithubUser(UserDataDto githubUserDataDto) {
        UserDataResponse userDataResponse = new UserDataResponse();
        userDataResponse.setId(githubUserDataDto.getId());
        userDataResponse.setLogin(githubUserDataDto.getLogin());
        userDataResponse.setName(githubUserDataDto.getName());
        userDataResponse.setType(githubUserDataDto.getType());
        userDataResponse.setAvatarUrl(githubUserDataDto.getAvatarUrl());
        userDataResponse.setCreatedAt(githubUserDataDto.getCreatedAt());
        return userDataResponse;
    }
}
