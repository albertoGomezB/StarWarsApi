package org.agb.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.agb.swapi.utility.FilmDTODeserializer;

import java.util.List;

@Data
public class PersonDTO {

    private String name;
    private String birth_year;
    private String gender;
    private String planet_name;
    @JsonDeserialize(contentUsing = FilmDTODeserializer.class)
    private List<FilmDTO> films;
}
