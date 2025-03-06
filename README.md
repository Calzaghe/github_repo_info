# Github Repository Info API

## Description

This project is a Quarkus-based API that fetches information about a user's public GitHub repositories, excluding forks. 
The API returns details about repositories, their owner, and a list of branches with the latest commit SHA.

## Technologies

- **Java** + **Quarkus 3**
- **Jakarta RESTful Web Services (JAX-RS)**
- **MicroProfile REST Client**
- **SmallRye Mutiny** (reactive programming)
- **Lombok**
- **GitHub API**

## Project Structure

### Models

- `Commit` - Stores the commit SHA.
- `GithubBranch` - Represents a repository branch, containing its name and the latest commit.
- `GithubOwner` - Represents the repository owner.
- `GithubRepo` - Stores repository details.

### REST Client

- `GithubRestClient` - Interface communicating with the GitHub API to fetch repositories and branches.

### Error Handling

- `CustomError` - Custom model for storing error information (status and message).

### Service

- `GithubService` - Fetches user repositories, filters out forks, retrieves branches, and returns processed data as `Uni<Response>`.

### Controller

- `GithubController` - Handles REST requests and provides the following endpoint:
    - `GET /github/repos/{username}` - Fetches public repository information for a user.

## Installation & Running

1. Clone the repository:
   ```sh
   git clone https://github.com/Calzaghe/github_repo_info
   ```
2. Navigate to the project directory:
   ```sh
   cd github_repo_info
   ```
3. Start the Quarkus application:
   ```sh
   ./mvnw quarkus:dev
   ```

## Configuration

Add the following configuration in `application.properties`:

```
github-api/mp-rest/url=https://api.github.com
github-api/mp-rest/connectTimeout= !!SET CUSTOM TIMEOUT VALUE HERE!!
github-api/mp-rest/readTimeout= !!SET CUSTOM TIMEOUT VALUE HERE!!
```

## Usage Examples

### Fetching User Repositories

**Request:**
```
GET /github/repos/{username}
```

**Example happy response:**
```json
[
  {
    "repositoryName": "my-project",
    "ownerLogin": "username",
    "branches": [
      {
        "name": "main",
        "sha": "abc123"
      }
    ]
  }
]
```

**Example error response:**
```json
{
    "status": 404,
    "message": "No public repositories found for user ${username}"
}
```

## Author
Created by: PBania 


