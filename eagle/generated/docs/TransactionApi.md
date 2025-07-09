# TransactionApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createTransaction**](TransactionApi.md#createTransaction) | **POST** /v1/accounts/{accountNumber}/transactions |  |
| [**fetchAccountTransactionByID**](TransactionApi.md#fetchAccountTransactionByID) | **GET** /v1/accounts/{accountNumber}/transactions/{transactionId} |  |
| [**listAccountTransaction**](TransactionApi.md#listAccountTransaction) | **GET** /v1/accounts/{accountNumber}/transactions |  |


<a id="createTransaction"></a>
# **createTransaction**
> TransactionResponse createTransaction(accountNumber, createTransactionRequest)



Create a transaction

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    TransactionApi apiInstance = new TransactionApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(); // CreateTransactionRequest | Create a new transaction
    try {
      TransactionResponse result = apiInstance.createTransaction(accountNumber, createTransactionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApi#createTransaction");
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
| **createTransactionRequest** | [**CreateTransactionRequest**](CreateTransactionRequest.md)| Create a new transaction | |

### Return type

[**TransactionResponse**](TransactionResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Transaction has been created successfully |  -  |
| **400** | Invalid details supplied |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to delete the bank account details |  -  |
| **404** | Bank account was not found |  -  |
| **422** | Insufficient funds to process transaction |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="fetchAccountTransactionByID"></a>
# **fetchAccountTransactionByID**
> TransactionResponse fetchAccountTransactionByID(accountNumber, transactionId)



Fetch transaction by ID.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    TransactionApi apiInstance = new TransactionApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    String transactionId = "transactionId_example"; // String | ID of the transaction
    try {
      TransactionResponse result = apiInstance.fetchAccountTransactionByID(accountNumber, transactionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApi#fetchAccountTransactionByID");
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
| **transactionId** | **String**| ID of the transaction | |

### Return type

[**TransactionResponse**](TransactionResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The transaction details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transaction |  -  |
| **404** | Bank account was not found |  -  |
| **500** | An unexpected error occurred |  -  |

<a id="listAccountTransaction"></a>
# **listAccountTransaction**
> ListTransactionsResponse listAccountTransaction(accountNumber)



List transactions

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    TransactionApi apiInstance = new TransactionApi(defaultClient);
    String accountNumber = "accountNumber_example"; // String | Account number of the bank account
    try {
      ListTransactionsResponse result = apiInstance.listAccountTransaction(accountNumber);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApi#listAccountTransaction");
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

[**ListTransactionsResponse**](ListTransactionsResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The list of transaction details |  -  |
| **400** | The request didn&#39;t supply all the necessary data |  -  |
| **401** | Access token is missing or invalid |  -  |
| **403** | The user is not allowed to access the transactions |  -  |
| **404** | Bank account was not found |  -  |
| **500** | An unexpected error occurred |  -  |

