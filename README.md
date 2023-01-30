# Keycloak

This project provides an example of how to set up and use Keycloak with a simple user controller and security
configuration.

## Getting Started

1. Clone the repository
2. Go to the project root directory and execute the following command to start the Keycloak server and the Postgres
   database:

   ```commandline
   docker-compose up -d
   ```

3. Build and run the project by executing the following command:

   ```commandline
   mvn clean install spring-boot:run
   ```

4. Access the Keycloak server at http://localhost:8080/auth
5. Use the default credentials (admin/admin) to log in to the Keycloak Administration Console.

## Importing Realms

You can import realms by placing your realm json files in the `/realms` directory of your host machine, and they will
automatically be imported into Keycloak when the container starts.

## User Controller

The `UserController` class handles authentication for users. It provides a `GET` endpoint at `/api/me` that returns the
current user's authentication information.

## Security Configuration

The SecurityConfig class enables web security and configures the application's security filter chain to require
authentication for any request and uses JWT for OAuth2 resource server configuration.

**Note**

- The KC_POSTGRES_IMAGE_TAG and POSTGRES_CONTAINER_NAME are set to default value, if they are not set.
- The KEYCLOAK_ADMIN and KEYCLOAK_ADMIN_PASSWORD are set to default value, if they are not set.
- The KC_FEATURES are set to preview, if they are not set.
- The KEYCLOAK_ADMIN and KEYCLOAK_ADMIN_PASSWORD are admin, if they are not set.

## Test

To test the app after it has been launched, a request is sent to the Keycloak server through the use of the curl
command:

   ```commandline
   curl --location --request POST 'http://localhost:8080/realms/app/protocol/openid-connect/token' \
   --header 'Content-Type: application/x-www-form-urlencoded' \
   --data-urlencode 'client_id=your_client_id' \
   --data-urlencode 'client_secret=your_client_secret' \
   --data-urlencode 'grant_type=client_credentials'
   ```

The request is for an access token using the client credentials flow and requires the client_id, client_secret, and
grant_type as parameters. The client_id is the name of the client that was created in a previous step, the client_secret
is the secret configured for the client on the Keycloak admin console, and the grant_type is the client_credentials. The
realm name should be used to replace "app" in the URL.

After receiving the access token, it can be used to test the application by sending a GET request to the API endpoint
and including the token in the authorization header.

   ```commandline
   curl --location --request GET 'http://localhost:8081/api/me' \
   --header 'Authorization: Bearer <token_from_previous_step>'
   ```

## Built With

- [Keycloak](https://www.keycloak.org/) - Open source identity and access management solution
- [PostgreSQL](https://www.postgresql.org/) - Open source relational database management system
- [Docker](https://www.docker.com/) - Platform for building, shipping, and running distributed applications
- [Docker Compose](https://docs.docker.com/compose/) - Tool for defining and running multi-container Docker applications
