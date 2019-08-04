package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.model.InlineResponse200FilterAttrs;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200Releases
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-04T13:20:05.015979200+02:00[Europe/Berlin]")

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

  @JsonProperty("short_description")
  private String shortDescription;

  @JsonProperty("filter_attrs")
  private InlineResponse200FilterAttrs filterAttrs = null;

  @JsonProperty("primary_image")
  private String primaryImage;

  @JsonProperty("website")
  private String website;

  @JsonProperty("background_image")
  private String backgroundImage;

  @JsonProperty("shop_url")
  private String shopUrl;

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

  public InlineResponse200Releases shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

  /**
   * Get shortDescription
   * @return shortDescription
  */
  @ApiModelProperty(value = "")


  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public InlineResponse200Releases filterAttrs(InlineResponse200FilterAttrs filterAttrs) {
    this.filterAttrs = filterAttrs;
    return this;
  }

  /**
   * Get filterAttrs
   * @return filterAttrs
  */
  @ApiModelProperty(value = "")

  @Valid

  public InlineResponse200FilterAttrs getFilterAttrs() {
    return filterAttrs;
  }

  public void setFilterAttrs(InlineResponse200FilterAttrs filterAttrs) {
    this.filterAttrs = filterAttrs;
  }

  public InlineResponse200Releases primaryImage(String primaryImage) {
    this.primaryImage = primaryImage;
    return this;
  }

  /**
   * Get primaryImage
   * @return primaryImage
  */
  @ApiModelProperty(value = "")


  public String getPrimaryImage() {
    return primaryImage;
  }

  public void setPrimaryImage(String primaryImage) {
    this.primaryImage = primaryImage;
  }

  public InlineResponse200Releases website(String website) {
    this.website = website;
    return this;
  }

  /**
   * Get website
   * @return website
  */
  @ApiModelProperty(value = "")


  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public InlineResponse200Releases backgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
    return this;
  }

  /**
   * Get backgroundImage
   * @return backgroundImage
  */
  @ApiModelProperty(value = "")


  public String getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  public InlineResponse200Releases shopUrl(String shopUrl) {
    this.shopUrl = shopUrl;
    return this;
  }

  /**
   * Get shopUrl
   * @return shopUrl
  */
  @ApiModelProperty(value = "")


  public String getShopUrl() {
    return shopUrl;
  }

  public void setShopUrl(String shopUrl) {
    this.shopUrl = shopUrl;
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
        Objects.equals(this.originalReleaseString, inlineResponse200Releases.originalReleaseString) &&
        Objects.equals(this.shortDescription, inlineResponse200Releases.shortDescription) &&
        Objects.equals(this.filterAttrs, inlineResponse200Releases.filterAttrs) &&
        Objects.equals(this.primaryImage, inlineResponse200Releases.primaryImage) &&
        Objects.equals(this.website, inlineResponse200Releases.website) &&
        Objects.equals(this.backgroundImage, inlineResponse200Releases.backgroundImage) &&
        Objects.equals(this.shopUrl, inlineResponse200Releases.shopUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, dateFrom, dateTo, unknownReleaseDate, originalReleaseString, shortDescription, filterAttrs, primaryImage, website, backgroundImage, shopUrl);
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
    sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
    sb.append("    filterAttrs: ").append(toIndentedString(filterAttrs)).append("\n");
    sb.append("    primaryImage: ").append(toIndentedString(primaryImage)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    backgroundImage: ").append(toIndentedString(backgroundImage)).append("\n");
    sb.append("    shopUrl: ").append(toIndentedString(shopUrl)).append("\n");
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

