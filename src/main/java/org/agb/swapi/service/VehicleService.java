package org.agb.swapi.service;

import org.agb.swapi.dto.StarshipDTO;
import org.agb.swapi.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    String getFastestVehicleOrStarshipDrivenName(List<VehicleDTO> vehiclesDTO, List<StarshipDTO> starshipsDTO);
}
