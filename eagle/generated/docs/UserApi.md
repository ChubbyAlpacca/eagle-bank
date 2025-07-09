# UserApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createUser**](UserApi.md#createUser) | **POST** /v1/users |  |
| [**deleteUserByID**](UserApi.md#deleteUserByID) | **DELETE** /v1/users/{userId} |  |
| [**fetchUserByID**](UserApi.md#fetchUserByID) | **GET** /v1/users/{userId} |  |
| [**updateUserByID**](UserApi.md#updateUserByID) | **PATCH** /v1/users/{userId} |  |


<a id="createUser"></a>
# **createUser**
> UserResponse createUser(createUserRequest)



Create a new user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    UserApi apiInstance = new UserApi(defaultClient);
    CreateUserRequest createUserRequest = new CreateUserRequest(); // CreateUserRequest | Create a new user
    try {
      UserResponse result = apiInstance.createUser(createUserRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#createUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **createUserRequest** | [**CreateUserRequest**](CreateUserRequest.md)| Create a new user | |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | User has been created successfully |  -  |
| **400** | Invalid details supplied |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="deleteUserByID"></a>
# **deleteUserByID**
> deleteUserByID(userId)



Delete user by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String userId = "userId_example"; // String | ID of the user
    try {
      apiInstance.deleteUserByID(userId);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#deleteUserByID");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | **String**| ID of the user | |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | The user has been deleted |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **404** | User was not found |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transaction |  -  |
| **409** | A user cannot be deleted when they are associated with a bank account |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="fetchUserByID"></a>
# **fetchUserByID**
> UserResponse fetchUserByID(userId)



Fetch user by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String userId = "userId_example"; // String | ID of the user
    try {
      UserResponse result = apiInstance.fetchUserByID(userId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#fetchUserByID");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | **String**| ID of the user | |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The user details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transaction |  -  |
| **404** | User was not found |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="updateUserByID"></a>
# **updateUserByID**
> UserResponse updateUserByID(userId, updateUserRequest)



Update user by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String userId = "userId_example"; // String | ID of the user
    UpdateUserRequest updateUserRequest = new UpdateUserRequest(); // UpdateUserRequest | Update user details
    try {
      UserResponse result = apiInstance.updateUserByID(userId, updateUserRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#updateUserByID");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | **String**| ID of the user | |
| **updateUserRequest** | [**UpdateUserRequest**](UpdateUserRequest.md)| Update user details | |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The updated user details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transaction |  -  |
| **404** | User was not found |  -  |
| **500** | An unexpected error occurred |  -  |

