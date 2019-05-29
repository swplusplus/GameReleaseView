package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.InlineResponse200Releases;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse200
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-05-29T11:36:40.831023800+02:00[Europe/Berlin]")

public class InlineResponse200   {
  @JsonProperty("releases")
  @Valid
  private List<InlineResponse200Releases> releases = null;

  public InlineResponse200 releases(List<InlineResponse200Releases> releases) {
    this.releases = releases;
    return this;
  }

  public InlineResponse200 addReleasesItem(InlineResponse200Releases releasesItem) {
    if (this.releases == null) {
      this.releases = new ArrayList<>();
    }
    this.releases.add(releasesItem);
    return this;
  }

  /**
   * Get releases
   * @return releases
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<InlineResponse200Releases> getReleases() {
    return releases;
  }

  public void setReleases(List<InlineResponse200Releases> releases) {
    this.releases = releases;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return Objects.equals(this.releases, inlineResponse200.releases);
  }

  @Override
  public int hashCode() {
    return Objects.hash(releases);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    releases: ").append(toIndentedString(releases)).append("\n");
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

