# Github Repository Info API

## Description

This project is a Quarkus-based API that fetches information about a user's public GitHub repositories, excluding forks. The API returns details about repositories, their owner, and a list of branches with the latest commit SHA.

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

- `CustomError` - Model for storing error information (status and message).

### Service

- `GithubService` - Fetches user repositories, filters out forks, retrieves branches, and returns processed data as `Uni<Response>`.

### Controller

- `GithubController` - Handles REST requests and provides the following endpoint:
    - `GET /github/repos/{username}` - Fetches public repository information for a user.

## Installation & Running

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo.git
   ```
2. Navigate to the project directory:
   ```sh
   cd your-repo
   ```
3. Start the Quarkus application:
   ```sh
   ./mvnw quarkus:dev
   ```

## Configuration

Add the following configuration in `application.properties`:

```
quarkus.rest-client.github-api.url=https://api.github.com
```

## Usage Examples

### Fetching User Repositories

**Request:**
```
GET /github/repos/{username}
```

**Example Response:**
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

## Author
Created by: [Your Name]  
📧 Email: [Your Email]

