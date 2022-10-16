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

import java.io.Serial;
import java.io.Serializable;

/**
 * The vehicular restriction request.
 *
 * @param plate the plate
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
public record VehicularRestrictionRequest(String plate) implements Serializable {

    @Serial
    private static final long serialVersionUID = -3313683394529847718L;
}
