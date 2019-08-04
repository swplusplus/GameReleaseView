package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200FilterAttrs
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-04T13:20:05.015979200+02:00[Europe/Berlin]")

public class InlineResponse200FilterAttrs   {
  @JsonProperty("required_age")
  private Long requiredAge;

  @JsonProperty("spoken_languages")
  @Valid
  private List<String> spokenLanguages = null;

  @JsonProperty("ui_languages")
  @Valid
  private List<String> uiLanguages = null;

  @JsonProperty("subtitle_languages")
  @Valid
  private List<String> subtitleLanguages = null;

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

  public InlineResponse200FilterAttrs requiredAge(Long requiredAge) {
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

  public InlineResponse200FilterAttrs spokenLanguages(List<String> spokenLanguages) {
    this.spokenLanguages = spokenLanguages;
    return this;
  }

  public InlineResponse200FilterAttrs addSpokenLanguagesItem(String spokenLanguagesItem) {
    if (this.spokenLanguages == null) {
      this.spokenLanguages = new ArrayList<>();
    }
    this.spokenLanguages.add(spokenLanguagesItem);
    return this;
  }

  /**
   * Get spokenLanguages
   * @return spokenLanguages
  */
  @ApiModelProperty(value = "")


  public List<String> getSpokenLanguages() {
    return spokenLanguages;
  }

  public void setSpokenLanguages(List<String> spokenLanguages) {
    this.spokenLanguages = spokenLanguages;
  }

  public InlineResponse200FilterAttrs uiLanguages(List<String> uiLanguages) {
    this.uiLanguages = uiLanguages;
    return this;
  }

  public InlineResponse200FilterAttrs addUiLanguagesItem(String uiLanguagesItem) {
    if (this.uiLanguages == null) {
      this.uiLanguages = new ArrayList<>();
    }
    this.uiLanguages.add(uiLanguagesItem);
    return this;
  }

  /**
   * Get uiLanguages
   * @return uiLanguages
  */
  @ApiModelProperty(value = "")


  public List<String> getUiLanguages() {
    return uiLanguages;
  }

  public void setUiLanguages(List<String> uiLanguages) {
    this.uiLanguages = uiLanguages;
  }

  public InlineResponse200FilterAttrs subtitleLanguages(List<String> subtitleLanguages) {
    this.subtitleLanguages = subtitleLanguages;
    return this;
  }

  public InlineResponse200FilterAttrs addSubtitleLanguagesItem(String subtitleLanguagesItem) {
    if (this.subtitleLanguages == null) {
      this.subtitleLanguages = new ArrayList<>();
    }
    this.subtitleLanguages.add(subtitleLanguagesItem);
    return this;
  }

  /**
   * Get subtitleLanguages
   * @return subtitleLanguages
  */
  @ApiModelProperty(value = "")


  public List<String> getSubtitleLanguages() {
    return subtitleLanguages;
  }

  public void setSubtitleLanguages(List<String> subtitleLanguages) {
    this.subtitleLanguages = subtitleLanguages;
  }

  public InlineResponse200FilterAttrs developers(List<String> developers) {
    this.developers = developers;
    return this;
  }

  public InlineResponse200FilterAttrs addDevelopersItem(String developersItem) {
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

  public InlineResponse200FilterAttrs publishers(List<String> publishers) {
    this.publishers = publishers;
    return this;
  }

  public InlineResponse200FilterAttrs addPublishersItem(String publishersItem) {
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

  public InlineResponse200FilterAttrs categories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public InlineResponse200FilterAttrs addCategoriesItem(String categoriesItem) {
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

  public InlineResponse200FilterAttrs genres(List<String> genres) {
    this.genres = genres;
    return this;
  }

  public InlineResponse200FilterAttrs addGenresItem(String genresItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200FilterAttrs inlineResponse200FilterAttrs = (InlineResponse200FilterAttrs) o;
    return Objects.equals(this.requiredAge, inlineResponse200FilterAttrs.requiredAge) &&
        Objects.equals(this.spokenLanguages, inlineResponse200FilterAttrs.spokenLanguages) &&
        Objects.equals(this.uiLanguages, inlineResponse200FilterAttrs.uiLanguages) &&
        Objects.equals(this.subtitleLanguages, inlineResponse200FilterAttrs.subtitleLanguages) &&
        Objects.equals(this.developers, inlineResponse200FilterAttrs.developers) &&
        Objects.equals(this.publishers, inlineResponse200FilterAttrs.publishers) &&
        Objects.equals(this.categories, inlineResponse200FilterAttrs.categories) &&
        Objects.equals(this.genres, inlineResponse200FilterAttrs.genres);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requiredAge, spokenLanguages, uiLanguages, subtitleLanguages, developers, publishers, categories, genres);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200FilterAttrs {\n");
    
    sb.append("    requiredAge: ").append(toIndentedString(requiredAge)).append("\n");
    sb.append("    spokenLanguages: ").append(toIndentedString(spokenLanguages)).append("\n");
    sb.append("    uiLanguages: ").append(toIndentedString(uiLanguages)).append("\n");
    sb.append("    subtitleLanguages: ").append(toIndentedString(subtitleLanguages)).append("\n");
    sb.append("    developers: ").append(toIndentedString(developers)).append("\n");
    sb.append("    publishers: ").append(toIndentedString(publishers)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
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

