package com.bartoszkaczmarek.recruitment.controllers;

import com.bartoszkaczmarek.recruitment.domain.LoginRequestCount;
import com.bartoszkaczmarek.recruitment.domain.UserDataDto;
import com.bartoszkaczmarek.recruitment.repositories.LoginRequestCountRepository;
import com.bartoszkaczmarek.recruitment.services.GithubRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZonedDateTime;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserRestControllerTest {

    @MockBean
    GithubRestClient githubRestClient;

    @MockBean
    LoginRequestCountRepository loginRequestCountRepository;

    @Test
    void shouldFindExistingUser() {
        when(githubRestClient.getGithubUser("octocat"))
                .thenReturn(new UserDataDto(1L, "octocat", "The Octocat", "User",
                        "https://avatars.githubusercontent.com/u/583231?v=4", 5, 50,
                        ZonedDateTime.parse("2011-01-25T18:44:36Z")));

        given().when().get("http://localhost:8080/users/octocat")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("login", equalTo("octocat"))
                .body("name", equalTo("The Octocat"))
                .body("type", equalTo("User"))
                .body("avatarUrl", equalTo("https://avatars.githubusercontent.com/u/583231?v=4"))
                .body("createdAt", equalTo("2011-01-25T18:44:36Z"))
                .body("calculations", equalTo(0.84f));
    }

    @Test
    void shouldPassStatusCodeWhen404() {
        when(githubRestClient.getGithubUser("octocat"))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        given().when().get("http://localhost:8080/users/octocat")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldResolveCalculationsWhenDividedBy0() {
        when(githubRestClient.getGithubUser("octocat"))
                .thenReturn(new UserDataDto(1L, "octocat", "The Octocat", "User",
                        "https://avatars.githubusercontent.com/u/583231?v=4", 0, 0,
                        ZonedDateTime.parse("2011-01-25T18:44:36Z")));

        given().when().get("http://localhost:8080/users/octocat")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("login", equalTo("octocat"))
                .body("name", equalTo("The Octocat"))
                .body("type", equalTo("User"))
                .body("avatarUrl", equalTo("https://avatars.githubusercontent.com/u/583231?v=4"))
                .body("createdAt", equalTo("2011-01-25T18:44:36Z"))
                .body("calculations", equalTo(0.0f));
    }

    @Test
    void shouldVerifyThatRepositoryWasCalled3Times() {
        when(githubRestClient.getGithubUser("octocat"))
                .thenReturn(new UserDataDto(1L, "octocat", "The Octocat", "User",
                        "https://avatars.githubusercontent.com/u/583231?v=4", 0, 0,
                        ZonedDateTime.parse("2011-01-25T18:44:36Z")));

        when(loginRequestCountRepository.findByLogin("octocat")).thenReturn(Optional.of(new LoginRequestCount("octocat", 3)));

        given().when().get("http://localhost:8080/users/octocat");

        ArgumentCaptor<LoginRequestCount> loginRequestCountCaptor = ArgumentCaptor.forClass(LoginRequestCount.class);

        verify(loginRequestCountRepository, times(1)).save(loginRequestCountCaptor.capture());

        LoginRequestCount capturedLoginRequestCount = loginRequestCountCaptor.getValue();
        assertEquals(4, capturedLoginRequestCount.getRequestCount());
    }

}
