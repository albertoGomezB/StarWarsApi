package org.agb.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO implements Serializable {

   // TODO: Change title to name
   private String title;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private String url;
   private String release_date;

   public FilmDTO(String url) {
       this.url = url;
   }

}
