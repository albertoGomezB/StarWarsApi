package org.agb.swapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StarshipDTO {

    private String name;
    private String max_atmosphering_speed;
    private String url;

    public StarshipDTO(String url) {
        this.url = url;
    }
}
