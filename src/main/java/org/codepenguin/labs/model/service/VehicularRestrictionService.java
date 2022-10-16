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

package org.codepenguin.labs.model.service;

import org.apache.commons.lang3.StringUtils;
import org.codepenguin.labs.model.domain.VehicularRestrictionRequest;
import org.codepenguin.labs.model.domain.VehicularRestrictionResponse;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.lang.Boolean.TRUE;
import static org.codepenguin.labs.model.enums.ResponseStatus.ERROR;
import static org.codepenguin.labs.model.enums.ResponseStatus.OK;

/**
 * Business service for vehicular restrictions.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@ApplicationScoped
public class VehicularRestrictionService {

    private static final String SUCCESSFUL = "Successful";

    /**
     * Evaluates if the vehicle's plate can drive now in Bogotá.
     *
     * @param request the request
     * @return the vehicular restriction response
     */
    public VehicularRestrictionResponse evaluate(final VehicularRestrictionRequest request) {
        final var plate = request.plate();
        if (StringUtils.isBlank(plate)) {
            return new VehicularRestrictionResponse(ERROR, "Must evaluate a plate", request,
                    null, null);
        }

        final var lastChar = plate.charAt(plate.length() - 1);
        if (!Character.isDigit(lastChar)) {
            return new VehicularRestrictionResponse(ERROR, "Last char of the plate must be a digit", request,
                    null, null);
        }

        final var now = LocalDateTime.now(ZoneId.of("America/Bogota"));

        return switch (now.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> new VehicularRestrictionResponse(OK, SUCCESSFUL, request, now, TRUE);
            default -> evaluateWeekdays(request, lastChar, now);
        };
    }

    private VehicularRestrictionResponse evaluateWeekdays(final VehicularRestrictionRequest request, final char lastChar,
                                                          final LocalDateTime now) {
        final var hour = now.getHour();
        return hour < 6 || 21 < hour ?
                new VehicularRestrictionResponse(OK, SUCCESSFUL, request, now, TRUE) :
                new VehicularRestrictionResponse(OK, SUCCESSFUL, request, now,
                        isEven(now.getDayOfMonth()) != isEven(Integer.parseInt(String.valueOf(lastChar))));

    }

    private boolean isEven(final int digit) {
        return digit % 2 == 0;
    }
}
