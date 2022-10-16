/*
 *     Copyright (C) 2022  CodePenguin.org - Jorge Garcia
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.codepenguin.labs.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codepenguin.labs.model.domain.VehicularRestrictionRequest;
import org.codepenguin.labs.model.service.VehicularRestrictionService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.codepenguin.labs.model.enums.ResponseStatus.ERROR;

/**
 * REST controller for vehicular restrictions.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@AllArgsConstructor
@Path("/vehicular-restriction")
@Slf4j
public class VehicularRestrictionResource {

    private final VehicularRestrictionService service;

    /**
     * Evaluates if the vehicle's plate can drive now in Bogotá.
     *
     * @param plate the plate
     * @return the response
     */
    @GET
    @Path("/{plate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response evaluate(@PathParam("plate") final String plate) {
        log.info(plate);

        final var response = service.evaluate(new VehicularRestrictionRequest(plate));
        log.info(String.valueOf(response));

        return response.status().equals(ERROR) ? Response.status(BAD_REQUEST).entity(response).build()
                : Response.ok(response).build();

    }
}