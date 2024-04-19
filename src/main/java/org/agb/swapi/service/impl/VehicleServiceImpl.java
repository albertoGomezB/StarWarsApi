package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.StarshipDTO;
import org.agb.swapi.dto.VehicleDTO;
import org.agb.swapi.exception.StarshipNotFoundException;
import org.agb.swapi.exception.VehicleNotFoundException;
import org.agb.swapi.service.VehicleService;
import org.agb.swapi.utility.ExtractUrl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents the implementation of the VehicleService interface.
 */

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {

    private final SWClient swClient;

    @Override
    public String getFastestVehicleOrStarshipDrivenName(List<VehicleDTO> vehiclesDTO, List<StarshipDTO> starshipsDTO) {

        String fastestDriven = null;
        String maxSpeed = "0";
        String currentSpeed;

        try {
            for (VehicleDTO vehicleDTO : vehiclesDTO) {

                String vehicleId = ExtractUrl.extractLastTwoCharactersFromUrl(vehicleDTO.getUrl()); // Extract the vehicle id
                VehicleDTO updatedVehicleDTO = swClient.getVehicleById(vehicleId); // Get the vehicle information

                if (updatedVehicleDTO != null && updatedVehicleDTO.getMax_atmosphering_speed() != null) {

                    currentSpeed = updatedVehicleDTO.getMax_atmosphering_speed();
                    if (Integer.parseInt(currentSpeed) > Integer.parseInt(maxSpeed)) {
                        maxSpeed = currentSpeed; // Set the max speed to the current speed value
                        fastestDriven = updatedVehicleDTO.getName(); // Set the fastest vehicle driven name
                    }
                }
            }

            for (StarshipDTO starshipDTO : starshipsDTO) {

                String starshipId = ExtractUrl.extractLastTwoCharactersFromUrl(starshipDTO.getUrl()); // Extract the starship id
                StarshipDTO updatedStarshipDTO = swClient.getStarshipById(starshipId); // Get the starship information

                if (updatedStarshipDTO != null && updatedStarshipDTO.getMax_atmosphering_speed() != null) {
                    currentSpeed = updatedStarshipDTO.getMax_atmosphering_speed();
                    if (Integer.parseInt(currentSpeed) > Integer.parseInt(maxSpeed)) {
                        maxSpeed = currentSpeed; // Set the max speed to the starship speed if it is greater than the current max speed
                        fastestDriven = updatedStarshipDTO.getName(); // Set the fastest starship driven to the starship name
                    }
                }
            }
        } catch (VehicleNotFoundException e) {
            throw new VehicleNotFoundException("Vehicle not found");
        } catch (StarshipNotFoundException e) {
            throw new StarshipNotFoundException("Starship not found");
        }
        return fastestDriven;
    }

}
