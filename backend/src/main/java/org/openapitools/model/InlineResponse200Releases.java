package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.InlineResponse200Languages;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200Releases
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-07-14T15:14:17.707425200+02:00[Europe/Berlin]")

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

  @JsonProperty("required_age")
  private Long requiredAge;

  @JsonProperty("short_description")
  private String shortDescription;

  @JsonProperty("languages")
  @Valid
  private List<InlineResponse200Languages> languages = null;

  @JsonProperty("primary_image")
  private String primaryImage;

  @JsonProperty("website")
  private String website;

  @JsonProperty("developers")
  @Valid
  private List<String> developers = null;

  @JsonProperty("publishers")
  @Valid
  private List<String> publishers = null;

  @JsonProperty("categories")
  @Valid
  private List<String> categories = null;

  @JsonProperty("genres")
  @Valid
  private List<String> genres = null;

  @JsonProperty("background_image")
  private String backgroundImage;

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

  public InlineResponse200Releases requiredAge(Long requiredAge) {
    this.requiredAge = requiredAge;
    return this;
  }

  /**
   * Get requiredAge
   * @return requiredAge
  */
  @ApiModelProperty(value = "")


  public Long getRequiredAge() {
    return requiredAge;
  }

  public void setRequiredAge(Long requiredAge) {
    this.requiredAge = requiredAge;
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

  public InlineResponse200Releases languages(List<InlineResponse200Languages> languages) {
    this.languages = languages;
    return this;
  }

  public InlineResponse200Releases addLanguagesItem(InlineResponse200Languages languagesItem) {
    if (this.languages == null) {
      this.languages = new ArrayList<>();
    }
    this.languages.add(languagesItem);
    return this;
  }

  /**
   * Get languages
   * @return languages
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<InlineResponse200Languages> getLanguages() {
    return languages;
  }

  public void setLanguages(List<InlineResponse200Languages> languages) {
    this.languages = languages;
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

  public InlineResponse200Releases developers(List<String> developers) {
    this.developers = developers;
    return this;
  }

  public InlineResponse200Releases addDevelopersItem(String developersItem) {
    if (this.developers == null) {
      this.developers = new ArrayList<>();
    }
    this.developers.add(developersItem);
    return this;
  }

  /**
   * Get developers
   * @return developers
  */
  @ApiModelProperty(value = "")


  public List<String> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<String> developers) {
    this.developers = developers;
  }

  public InlineResponse200Releases publishers(List<String> publishers) {
    this.publishers = publishers;
    return this;
  }

  public InlineResponse200Releases addPublishersItem(String publishersItem) {
    if (this.publishers == null) {
      this.publishers = new ArrayList<>();
    }
    this.publishers.add(publishersItem);
    return this;
  }

  /**
   * Get publishers
   * @return publishers
  */
  @ApiModelProperty(value = "")


  public List<String> getPublishers() {
    return publishers;
  }

  public void setPublishers(List<String> publishers) {
    this.publishers = publishers;
  }

  public InlineResponse200Releases categories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public InlineResponse200Releases addCategoriesItem(String categoriesItem) {
    if (this.categories == null) {
      this.categories = new ArrayList<>();
    }
    this.categories.add(categoriesItem);
    return this;
  }

  /**
   * Get categories
   * @return categories
  */
  @ApiModelProperty(value = "")


  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public InlineResponse200Releases genres(List<String> genres) {
    this.genres = genres;
    return this;
  }

  public InlineResponse200Releases addGenresItem(String genresItem) {
    if (this.genres == null) {
      this.genres = new ArrayList<>();
    }
    this.genres.add(genresItem);
    return this;
  }

  /**
   * Get genres
   * @return genres
  */
  @ApiModelProperty(value = "")


  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
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
        Objects.equals(this.requiredAge, inlineResponse200Releases.requiredAge) &&
        Objects.equals(this.shortDescription, inlineResponse200Releases.shortDescription) &&
        Objects.equals(this.languages, inlineResponse200Releases.languages) &&
        Objects.equals(this.primaryImage, inlineResponse200Releases.primaryImage) &&
        Objects.equals(this.website, inlineResponse200Releases.website) &&
        Objects.equals(this.developers, inlineResponse200Releases.developers) &&
        Objects.equals(this.publishers, inlineResponse200Releases.publishers) &&
        Objects.equals(this.categories, inlineResponse200Releases.categories) &&
        Objects.equals(this.genres, inlineResponse200Releases.genres) &&
        Objects.equals(this.backgroundImage, inlineResponse200Releases.backgroundImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, dateFrom, dateTo, unknownReleaseDate, originalReleaseString, requiredAge, shortDescription, languages, primaryImage, website, developers, publishers, categories, genres, backgroundImage);
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
    sb.append("    requiredAge: ").append(toIndentedString(requiredAge)).append("\n");
    sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
    sb.append("    languages: ").append(toIndentedString(languages)).append("\n");
    sb.append("    primaryImage: ").append(toIndentedString(primaryImage)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    developers: ").append(toIndentedString(developers)).append("\n");
    sb.append("    publishers: ").append(toIndentedString(publishers)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
    sb.append("    backgroundImage: ").append(toIndentedString(backgroundImage)).append("\n");
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

