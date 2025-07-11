/*
 * Take home tech task
 * We want you to create a REST API for Eagle Bank which conforms to this OpenAPI specification which allows a user to create, fetch, update and delete a bank account and deposit or withdraw money from the account. These will be stored as transactions against a bank account which be retrieved but not modified or deleted.
 *
 * The version of the OpenAPI document: v1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.BadRequestErrorResponse;
import org.openapitools.client.model.CreateTransactionRequest;
import org.openapitools.client.model.ErrorResponse;
import org.openapitools.client.model.ListTransactionsResponse;
import org.openapitools.client.model.TransactionResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public TransactionApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TransactionApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for createTransaction
     * @param accountNumber Account number of the bank account (required)
     * @param createTransactionRequest Create a new transaction (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Transaction has been created successfully </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid details supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to delete the bank account details </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Insufficient funds to process transaction </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createTransactionCall(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull CreateTransactionRequest createTransactionRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = createTransactionRequest;

        // create path and map variables
        String localVarPath = "/v1/accounts/{accountNumber}/transactions"
            .replace("{" + "accountNumber" + "}", localVarApiClient.escapeString(accountNumber.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createTransactionValidateBeforeCall(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull CreateTransactionRequest createTransactionRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'accountNumber' is set
        if (accountNumber == null) {
            throw new ApiException("Missing the required parameter 'accountNumber' when calling createTransaction(Async)");
        }

        // verify the required parameter 'createTransactionRequest' is set
        if (createTransactionRequest == null) {
            throw new ApiException("Missing the required parameter 'createTransactionRequest' when calling createTransaction(Async)");
        }

        return createTransactionCall(accountNumber, createTransactionRequest, _callback);

    }

    /**
     * 
     * Create a transaction
     * @param accountNumber Account number of the bank account (required)
     * @param createTransactionRequest Create a new transaction (required)
     * @return TransactionResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Transaction has been created successfully </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid details supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to delete the bank account details </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Insufficient funds to process transaction </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public TransactionResponse createTransaction(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull CreateTransactionRequest createTransactionRequest) throws ApiException {
        ApiResponse<TransactionResponse> localVarResp = createTransactionWithHttpInfo(accountNumber, createTransactionRequest);
        return localVarResp.getData();
    }

    /**
     * 
     * Create a transaction
     * @param accountNumber Account number of the bank account (required)
     * @param createTransactionRequest Create a new transaction (required)
     * @return ApiResponse&lt;TransactionResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Transaction has been created successfully </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid details supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to delete the bank account details </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Insufficient funds to process transaction </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<TransactionResponse> createTransactionWithHttpInfo(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull CreateTransactionRequest createTransactionRequest) throws ApiException {
        okhttp3.Call localVarCall = createTransactionValidateBeforeCall(accountNumber, createTransactionRequest, null);
        Type localVarReturnType = new TypeToken<TransactionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Create a transaction
     * @param accountNumber Account number of the bank account (required)
     * @param createTransactionRequest Create a new transaction (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 201 </td><td> Transaction has been created successfully </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid details supplied </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to delete the bank account details </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Insufficient funds to process transaction </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createTransactionAsync(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull CreateTransactionRequest createTransactionRequest, final ApiCallback<TransactionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = createTransactionValidateBeforeCall(accountNumber, createTransactionRequest, _callback);
        Type localVarReturnType = new TypeToken<TransactionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for fetchAccountTransactionByID
     * @param accountNumber Account number of the bank account (required)
     * @param transactionId ID of the transaction (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transaction </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call fetchAccountTransactionByIDCall(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull String transactionId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/accounts/{accountNumber}/transactions/{transactionId}"
            .replace("{" + "accountNumber" + "}", localVarApiClient.escapeString(accountNumber.toString()))
            .replace("{" + "transactionId" + "}", localVarApiClient.escapeString(transactionId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call fetchAccountTransactionByIDValidateBeforeCall(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull String transactionId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'accountNumber' is set
        if (accountNumber == null) {
            throw new ApiException("Missing the required parameter 'accountNumber' when calling fetchAccountTransactionByID(Async)");
        }

        // verify the required parameter 'transactionId' is set
        if (transactionId == null) {
            throw new ApiException("Missing the required parameter 'transactionId' when calling fetchAccountTransactionByID(Async)");
        }

        return fetchAccountTransactionByIDCall(accountNumber, transactionId, _callback);

    }

    /**
     * 
     * Fetch transaction by ID.
     * @param accountNumber Account number of the bank account (required)
     * @param transactionId ID of the transaction (required)
     * @return TransactionResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transaction </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public TransactionResponse fetchAccountTransactionByID(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull String transactionId) throws ApiException {
        ApiResponse<TransactionResponse> localVarResp = fetchAccountTransactionByIDWithHttpInfo(accountNumber, transactionId);
        return localVarResp.getData();
    }

    /**
     * 
     * Fetch transaction by ID.
     * @param accountNumber Account number of the bank account (required)
     * @param transactionId ID of the transaction (required)
     * @return ApiResponse&lt;TransactionResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transaction </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<TransactionResponse> fetchAccountTransactionByIDWithHttpInfo(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull String transactionId) throws ApiException {
        okhttp3.Call localVarCall = fetchAccountTransactionByIDValidateBeforeCall(accountNumber, transactionId, null);
        Type localVarReturnType = new TypeToken<TransactionResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Fetch transaction by ID.
     * @param accountNumber Account number of the bank account (required)
     * @param transactionId ID of the transaction (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transaction </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call fetchAccountTransactionByIDAsync(@javax.annotation.Nonnull String accountNumber, @javax.annotation.Nonnull String transactionId, final ApiCallback<TransactionResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = fetchAccountTransactionByIDValidateBeforeCall(accountNumber, transactionId, _callback);
        Type localVarReturnType = new TypeToken<TransactionResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listAccountTransaction
     * @param accountNumber Account number of the bank account (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The list of transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transactions </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listAccountTransactionCall(@javax.annotation.Nonnull String accountNumber, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/v1/accounts/{accountNumber}/transactions"
            .replace("{" + "accountNumber" + "}", localVarApiClient.escapeString(accountNumber.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call listAccountTransactionValidateBeforeCall(@javax.annotation.Nonnull String accountNumber, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'accountNumber' is set
        if (accountNumber == null) {
            throw new ApiException("Missing the required parameter 'accountNumber' when calling listAccountTransaction(Async)");
        }

        return listAccountTransactionCall(accountNumber, _callback);

    }

    /**
     * 
     * List transactions
     * @param accountNumber Account number of the bank account (required)
     * @return ListTransactionsResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The list of transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transactions </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public ListTransactionsResponse listAccountTransaction(@javax.annotation.Nonnull String accountNumber) throws ApiException {
        ApiResponse<ListTransactionsResponse> localVarResp = listAccountTransactionWithHttpInfo(accountNumber);
        return localVarResp.getData();
    }

    /**
     * 
     * List transactions
     * @param accountNumber Account number of the bank account (required)
     * @return ApiResponse&lt;ListTransactionsResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The list of transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transactions </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ListTransactionsResponse> listAccountTransactionWithHttpInfo(@javax.annotation.Nonnull String accountNumber) throws ApiException {
        okhttp3.Call localVarCall = listAccountTransactionValidateBeforeCall(accountNumber, null);
        Type localVarReturnType = new TypeToken<ListTransactionsResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * List transactions
     * @param accountNumber Account number of the bank account (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The list of transaction details </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request didn&#39;t supply all the necessary data </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Access token is missing or invalid </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> The user is not allowed to access the transactions </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Bank account was not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An unexpected error occurred </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call listAccountTransactionAsync(@javax.annotation.Nonnull String accountNumber, final ApiCallback<ListTransactionsResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = listAccountTransactionValidateBeforeCall(accountNumber, _callback);
        Type localVarReturnType = new TypeToken<ListTransactionsResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
