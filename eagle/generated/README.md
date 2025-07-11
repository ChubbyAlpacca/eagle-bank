# openapi-java-client

Take home tech task
- API version: v1.0.0
  - Build date: 2025-07-09T20:25:24.647697+01:00[Europe/London]
  - Generator version: 7.14.0

We want you to create a REST API for Eagle Bank which conforms to this OpenAPI specification which allows a user to create, fetch, update and delete a bank account and deposit or withdraw money from the account. These will be stored as transactions against a bank account which be retrieved but not modified or deleted.


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>v1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'openapi-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'openapi-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:openapi-java-client:v1.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-v1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.model.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AccountApi apiInstance = new AccountApi(defaultClient);
    CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest(); // CreateBankAccountRequest | Create a new bank account for the user
    try {
      BankAccountResponse result = apiInstance.createAccount(createBankAccountRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#createAccount");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountApi* | [**createAccount**](docs/AccountApi.md#createAccount) | **POST** /v1/accounts | 
*AccountApi* | [**deleteAccountByAccountNumber**](docs/AccountApi.md#deleteAccountByAccountNumber) | **DELETE** /v1/accounts/{accountNumber} | 
*AccountApi* | [**fetchAccountByAccountNumber**](docs/AccountApi.md#fetchAccountByAccountNumber) | **GET** /v1/accounts/{accountNumber} | 
*AccountApi* | [**listAccounts**](docs/AccountApi.md#listAccounts) | **GET** /v1/accounts | 
*AccountApi* | [**updateAccountByAccountNumber**](docs/AccountApi.md#updateAccountByAccountNumber) | **PATCH** /v1/accounts/{accountNumber} | 
*TransactionApi* | [**createTransaction**](docs/TransactionApi.md#createTransaction) | **POST** /v1/accounts/{accountNumber}/transactions | 
*TransactionApi* | [**fetchAccountTransactionByID**](docs/TransactionApi.md#fetchAccountTransactionByID) | **GET** /v1/accounts/{accountNumber}/transactions/{transactionId} | 
*TransactionApi* | [**listAccountTransaction**](docs/TransactionApi.md#listAccountTransaction) | **GET** /v1/accounts/{accountNumber}/transactions | 
*UserApi* | [**createUser**](docs/UserApi.md#createUser) | **POST** /v1/users | 
*UserApi* | [**deleteUserByID**](docs/UserApi.md#deleteUserByID) | **DELETE** /v1/users/{userId} | 
*UserApi* | [**fetchUserByID**](docs/UserApi.md#fetchUserByID) | **GET** /v1/users/{userId} | 
*UserApi* | [**updateUserByID**](docs/UserApi.md#updateUserByID) | **PATCH** /v1/users/{userId} | 


## Documentation for Models

 - [BadRequestErrorResponse](docs/BadRequestErrorResponse.md)
 - [BadRequestErrorResponseDetailsInner](docs/BadRequestErrorResponseDetailsInner.md)
 - [BankAccountResponse](docs/BankAccountResponse.md)
 - [CreateBankAccountRequest](docs/CreateBankAccountRequest.md)
 - [CreateTransactionRequest](docs/CreateTransactionRequest.md)
 - [CreateUserRequest](docs/CreateUserRequest.md)
 - [CreateUserRequestAddress](docs/CreateUserRequestAddress.md)
 - [ErrorResponse](docs/ErrorResponse.md)
 - [ListBankAccountsResponse](docs/ListBankAccountsResponse.md)
 - [ListTransactionsResponse](docs/ListTransactionsResponse.md)
 - [TransactionResponse](docs/TransactionResponse.md)
 - [UpdateBankAccountRequest](docs/UpdateBankAccountRequest.md)
 - [UpdateUserRequest](docs/UpdateUserRequest.md)
 - [UserResponse](docs/UserResponse.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="bearerAuth"></a>
### bearerAuth

- **Type**: HTTP Bearer Token authentication (JWT)


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



