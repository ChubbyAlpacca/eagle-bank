package com.takehome.eagle.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.takehome.eagle.model.TransactionResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ListTransactionsResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-16T12:06:38.809147+01:00[Europe/London]", comments = "Generator version: 7.6.0")
public class ListTransactionsResponse {

  @Valid
  private List<@Valid TransactionResponse> transactions = new ArrayList<>();

  public ListTransactionsResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ListTransactionsResponse(List<@Valid TransactionResponse> transactions) {
    this.transactions = transactions;
  }

  public ListTransactionsResponse transactions(List<@Valid TransactionResponse> transactions) {
    this.transactions = transactions;
    return this;
  }

  public ListTransactionsResponse addTransactionsItem(TransactionResponse transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

  /**
   * Get transactions
   * @return transactions
  */
  @NotNull @Valid 
  @Schema(name = "transactions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("transactions")
  public List<@Valid TransactionResponse> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<@Valid TransactionResponse> transactions) {
    this.transactions = transactions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListTransactionsResponse listTransactionsResponse = (ListTransactionsResponse) o;
    return Objects.equals(this.transactions, listTransactionsResponse.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListTransactionsResponse {\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

