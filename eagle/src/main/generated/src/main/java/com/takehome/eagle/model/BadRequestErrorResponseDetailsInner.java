package com.takehome.eagle.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BadRequestErrorResponseDetailsInner
 */

@JsonTypeName("BadRequestErrorResponse_details_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-17T09:32:55.241470+01:00[Europe/London]", comments = "Generator version: 7.6.0")
public class BadRequestErrorResponseDetailsInner {

  private String field;

  private String message;

  private String type;

  public BadRequestErrorResponseDetailsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BadRequestErrorResponseDetailsInner(String field, String message, String type) {
    this.field = field;
    this.message = message;
    this.type = type;
  }

  public BadRequestErrorResponseDetailsInner field(String field) {
    this.field = field;
    return this;
  }

  /**
   * Get field
   * @return field
  */
  @NotNull 
  @Schema(name = "field", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public BadRequestErrorResponseDetailsInner message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BadRequestErrorResponseDetailsInner type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BadRequestErrorResponseDetailsInner badRequestErrorResponseDetailsInner = (BadRequestErrorResponseDetailsInner) o;
    return Objects.equals(this.field, badRequestErrorResponseDetailsInner.field) &&
        Objects.equals(this.message, badRequestErrorResponseDetailsInner.message) &&
        Objects.equals(this.type, badRequestErrorResponseDetailsInner.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, message, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BadRequestErrorResponseDetailsInner {\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

