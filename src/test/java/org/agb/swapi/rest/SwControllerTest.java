package org.agb.swapi.rest;

import org.agb.swapi.data.DataSwapi;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.SwapiResponseDTO;
import org.agb.swapi.service.PersonService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@WebMvcTest(SWController.class)

class SwControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void testSearchPersonByName_ValidName_ReturnsPersonInfo() throws Exception {

        // Given
        PersonDTO personDTO = DataSwapi.getPersonData();
        List<FilmDTO> films = DataSwapi.getPersonData().getFilms();
        personDTO.setFilms(films);

        SwapiResponseDTO swapiResponseDTO = new SwapiResponseDTO();
        swapiResponseDTO.setResults(Collections.singletonList(personDTO));

        // When/Then
        when(personService.searchPersonByName(DataSwapi.getPersonData().getName())).thenReturn(swapiResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/swapi-proxy/person-info")
                        .param("name", DataSwapi.getPersonData().getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Luke Skywalker"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birth_year").value("19BBY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.planet_name").value("Tatooine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fastest_vehicle_driven").value("X-wing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.films", hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.films[*].name",
                        containsInAnyOrder("A New Hope", "The Empire Strikes Back", "Return of the Jedi", "Revenge of the Sith")));
    }

    @Test
    void testSearchPersonByName_InvalidName_ReturnsNotFound() throws Exception {

        // Given
        SwapiResponseDTO swapiResponseDTO = new SwapiResponseDTO();
        swapiResponseDTO.setResults(Collections.emptyList());

        // When/Then
        when(personService.searchPersonByName("InvalidName")).thenReturn(swapiResponseDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/swapi-proxy/person-info")
                        .param("name", "InvalidName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
