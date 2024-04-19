package org.agb.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PersonDTO implements Serializable {

    private String name;
    private String birth_year;
    private String gender;
    private String planet_name;
    private String fastest_vehicle_driven;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // This annotation is used to prevent the attribute field from being serialized
    private String homeworld;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<VehicleDTO> vehicles;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<StarshipDTO> starships;
    private List<FilmDTO> films;


}
