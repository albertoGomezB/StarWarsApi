package org.agb.swapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SwapiResponseDTO implements Serializable {

 private List<PersonDTO> results;

}
