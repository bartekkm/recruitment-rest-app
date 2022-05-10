# recruitment-rest-app
Simple REST service which uses Github API.  
Prepared for a recruitment assignemnt.

## Description
Simple REST service which get data from Github API: https://api.github.com/users/{login}  
(e.g. https://api.github.com/users/octocat) and return below information:
- Identifier
- Login
- Name
- Type
- URL address to avatar
- Creation date
- Calculations

The *Calculations* field returns the result of below formula  
`Calculations = 6 / followers * (2 + public_repos)`

Prepared service also records the number of calls for each login in a database.  
Database includes two columns: `LOGIN` and `REQUEST_COUNT`. `REQUEST_COUNT` increments on each call for the given login.

Project prepared in Java language with usage of Spring framework.

## How to use
### Build project
Project can be build using Gradle:  

    gradlew cleanBuild

### Run service
To run service, please execute below command:  

    gradlew bootRun

### Usage
**Request**  
`GET /users/{login}`  

    curl -i -H 'Accept: application/json' http://localhost:8080/users/octocat

**Response**

    {
        "id": 583231,
        "login": "octocat",
        "name": "The Octocat",
        "type": "User",
        "avatarUrl": "https://avatars.githubusercontent.com/u/583231?v=4",
        "createdAt": "2011-01-25T18:44:36Z",
        "calculations": 0.010371650569140911
    }

### Contact
Bartosz Kaczmarek: bartoszkm@gmail.com  
Project Link: [https://github.com/bartekkm/recruitment-rest-app](https://github.com/bartekkm/recruitment-rest-app)