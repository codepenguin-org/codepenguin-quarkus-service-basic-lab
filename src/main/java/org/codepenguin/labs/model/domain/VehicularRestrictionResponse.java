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

package org.codepenguin.labs.model.domain;

import org.codepenguin.labs.model.enums.ResponseStatus;

import java.time.LocalDateTime;

/**
 * The vehicular restriction response.
 *
 * @param status   the status.
 * @param message  the message.
 * @param request  the original request.
 * @param dateTime the current datetime in Bogotá.
 * @param canDrive {@code true} if the vehicle can drive now in Bogotá; otherwise {@code false}.
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
public record VehicularRestrictionResponse(ResponseStatus status, String message, VehicularRestrictionRequest request,
                                           LocalDateTime dateTime, Boolean canDrive) {
}
