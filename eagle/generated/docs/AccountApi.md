# AccountApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAccount**](AccountApi.md#createAccount) | **POST** /v1/accounts |  |
| [**deleteAccountByAccountNumber**](AccountApi.md#deleteAccountByAccountNumber) | **DELETE** /v1/accounts/{accountNumber} |  |
| [**fetchAccountByAccountNumber**](AccountApi.md#fetchAccountByAccountNumber) | **GET** /v1/accounts/{accountNumber} |  |
| [**listAccounts**](AccountApi.md#listAccounts) | **GET** /v1/accounts |  |
| [**updateAccountByAccountNumber**](AccountApi.md#updateAccountByAccountNumber) | **PATCH** /v1/accounts/{accountNumber} |  |


<a id="createAccount"></a>
# **createAccount**
> BankAccountResponse createAccount(createBankAccountRequest)



Create a new bank account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
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

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **createBankAccountRequest** | [**CreateBankAccountRequest**](CreateBankAccountRequest.md)| Create a new bank account for the user | |

### Return type

[**BankAccountResponse**](BankAccountResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Bank Account has been created successfully |  -  |
| **400** | Invalid details supplied |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transaction |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="deleteAccountByAccountNumber"></a>
# **deleteAccountByAccountNumber**
> deleteAccountByAccountNumber(accountNumber)



Delete account by account number.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    try {
      apiInstance.deleteAccountByAccountNumber(accountNumber);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#deleteAccountByAccountNumber");
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
| **accountNumber** | **String**| Account number of the bank account | |

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
| **204** | The bank account has been deleted |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to delete the bank account details |  -  |
| **404** | Bank account was not found |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="fetchAccountByAccountNumber"></a>
# **fetchAccountByAccountNumber**
> BankAccountResponse fetchAccountByAccountNumber(accountNumber)



Fetch account by account number.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    try {
      BankAccountResponse result = apiInstance.fetchAccountByAccountNumber(accountNumber);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#fetchAccountByAccountNumber");
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
| **accountNumber** | **String**| Account number of the bank account | |

### Return type

[**BankAccountResponse**](BankAccountResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The bank account details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | The user was not authenticated |  -  |
| **403** | The user is not allowed to access the bank account details |  -  |
| **404** | Bank account was not found |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="listAccounts"></a>
# **listAccounts**
> ListBankAccountsResponse listAccounts()



List accounts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AccountApi apiInstance = new AccountApi(defaultClient);
    try {
      ListBankAccountsResponse result = apiInstance.listAccounts();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#listAccounts");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ListBankAccountsResponse**](ListBankAccountsResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The list of bank accounts |  -  |
| **401** | Access token is missing or invalid |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="updateAccountByAccountNumber"></a>
# **updateAccountByAccountNumber**
> BankAccountResponse updateAccountByAccountNumber(accountNumber, updateBankAccountRequest)



Update account by account number.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    AccountApi apiInstance = new AccountApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    UpdateBankAccountRequest updateBankAccountRequest = new UpdateBankAccountRequest(); // UpdateBankAccountRequest | Update bank account details for the user
    try {
      BankAccountResponse result = apiInstance.updateAccountByAccountNumber(accountNumber, updateBankAccountRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountApi#updateAccountByAccountNumber");
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
| **accountNumber** | **String**| Account number of the bank account | |
| **updateBankAccountRequest** | [**UpdateBankAccountRequest**](UpdateBankAccountRequest.md)| Update bank account details for the user | |

### Return type

[**BankAccountResponse**](BankAccountResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The updated bank account details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to update the bank account details |  -  |
| **404** | Bank account was not found |  -  |
| **500** | An unexpected error occurred |  -  |

