package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200Releases
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-06-16T16:34:32.932323800+02:00[Europe/Berlin]")

public class InlineResponse200Releases   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("date_from")
  private LocalDate dateFrom;

  @JsonProperty("date_to")
  private LocalDate dateTo;

  @JsonProperty("unknown_release_date")
  private Boolean unknownReleaseDate;

  @JsonProperty("original_release_string")
  private String originalReleaseString;

  public InlineResponse200Releases id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public InlineResponse200Releases name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InlineResponse200Releases dateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
    return this;
  }

  /**
   * Get dateFrom
   * @return dateFrom
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
  }

  public InlineResponse200Releases dateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
    return this;
  }

  /**
   * Get dateTo
   * @return dateTo
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDateTo() {
    return dateTo;
  }

  public void setDateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
  }

  public InlineResponse200Releases unknownReleaseDate(Boolean unknownReleaseDate) {
    this.unknownReleaseDate = unknownReleaseDate;
    return this;
  }

  /**
   * Get unknownReleaseDate
   * @return unknownReleaseDate
  */
  @ApiModelProperty(value = "")


  public Boolean getUnknownReleaseDate() {
    return unknownReleaseDate;
  }

  public void setUnknownReleaseDate(Boolean unknownReleaseDate) {
    this.unknownReleaseDate = unknownReleaseDate;
  }

  public InlineResponse200Releases originalReleaseString(String originalReleaseString) {
    this.originalReleaseString = originalReleaseString;
    return this;
  }

  /**
   * Get originalReleaseString
   * @return originalReleaseString
  */
  @ApiModelProperty(value = "")


  public String getOriginalReleaseString() {
    return originalReleaseString;
  }

  public void setOriginalReleaseString(String originalReleaseString) {
    this.originalReleaseString = originalReleaseString;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200Releases inlineResponse200Releases = (InlineResponse200Releases) o;
    return Objects.equals(this.id, inlineResponse200Releases.id) &&
        Objects.equals(this.name, inlineResponse200Releases.name) &&
        Objects.equals(this.dateFrom, inlineResponse200Releases.dateFrom) &&
        Objects.equals(this.dateTo, inlineResponse200Releases.dateTo) &&
        Objects.equals(this.unknownReleaseDate, inlineResponse200Releases.unknownReleaseDate) &&
        Objects.equals(this.originalReleaseString, inlineResponse200Releases.originalReleaseString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, dateFrom, dateTo, unknownReleaseDate, originalReleaseString);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200Releases {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dateFrom: ").append(toIndentedString(dateFrom)).append("\n");
    sb.append("    dateTo: ").append(toIndentedString(dateTo)).append("\n");
    sb.append("    unknownReleaseDate: ").append(toIndentedString(unknownReleaseDate)).append("\n");
    sb.append("    originalReleaseString: ").append(toIndentedString(originalReleaseString)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

