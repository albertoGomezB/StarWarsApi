package org.agb.swapi.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import java.io.IOException;

import static org.agb.swapi.utility.ExtractUrl.extractIdFromUrl;

public class FilmDTODeserializer extends StdDeserializer<FilmDTO> {

    private final SWClient swClient;

    public FilmDTODeserializer(SWClient swClient) {
        super(FilmDTO.class);
        this.swClient = swClient;
    }

    @Override
    public FilmDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        // Obtain the URL from the JSON as a string
        String filmUrl = jsonParser.getValueAsString();

        // Extract the film ID from the URL
        String filmId = ExtractUrl.extractIdFromUrl(filmUrl);

        // Get the film details from the SWClient using the ID
        return swClient.getFilmById(filmId);
    }
}
