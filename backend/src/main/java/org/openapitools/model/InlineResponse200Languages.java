package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200Languages
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-07-14T15:14:17.707425200+02:00[Europe/Berlin]")

public class InlineResponse200Languages   {
  @JsonProperty("language")
  private String language;

  @JsonProperty("ui")
  private Boolean ui;

  @JsonProperty("spoken")
  private Boolean spoken;

  @JsonProperty("subtitles")
  private Boolean subtitles;

  public InlineResponse200Languages language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  */
  @ApiModelProperty(value = "")


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public InlineResponse200Languages ui(Boolean ui) {
    this.ui = ui;
    return this;
  }

  /**
   * Get ui
   * @return ui
  */
  @ApiModelProperty(value = "")


  public Boolean getUi() {
    return ui;
  }

  public void setUi(Boolean ui) {
    this.ui = ui;
  }

  public InlineResponse200Languages spoken(Boolean spoken) {
    this.spoken = spoken;
    return this;
  }

  /**
   * Get spoken
   * @return spoken
  */
  @ApiModelProperty(value = "")


  public Boolean getSpoken() {
    return spoken;
  }

  public void setSpoken(Boolean spoken) {
    this.spoken = spoken;
  }

  public InlineResponse200Languages subtitles(Boolean subtitles) {
    this.subtitles = subtitles;
    return this;
  }

  /**
   * Get subtitles
   * @return subtitles
  */
  @ApiModelProperty(value = "")


  public Boolean getSubtitles() {
    return subtitles;
  }

  public void setSubtitles(Boolean subtitles) {
    this.subtitles = subtitles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200Languages inlineResponse200Languages = (InlineResponse200Languages) o;
    return Objects.equals(this.language, inlineResponse200Languages.language) &&
        Objects.equals(this.ui, inlineResponse200Languages.ui) &&
        Objects.equals(this.spoken, inlineResponse200Languages.spoken) &&
        Objects.equals(this.subtitles, inlineResponse200Languages.subtitles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language, ui, spoken, subtitles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200Languages {\n");
    
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    ui: ").append(toIndentedString(ui)).append("\n");
    sb.append("    spoken: ").append(toIndentedString(spoken)).append("\n");
    sb.append("    subtitles: ").append(toIndentedString(subtitles)).append("\n");
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

