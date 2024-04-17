package org.agb.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FilmDTO {

   private String title;
   @JsonIgnore
   private String url;
   private String release_date;
}
