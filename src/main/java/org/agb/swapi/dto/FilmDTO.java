package org.agb.swapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Ignore the attribute when serializing the object
    private String title;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String url;
    private String name;
    private String release_date;

    public FilmDTO(String url) {
        this.url = url;
    }

}
