package com.bartoszkaczmarek.recruitment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class UserDataDto {

    private Long id;
    private String login;
    private String name;
    private String type;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("public_repos")
    private float publicRepos;
    private float followers;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    public UserDataDto(Long id, String login, String name, String type, String avatarUrl, int publicRepos, int followers, ZonedDateTime createdAt) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public float getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public float getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
