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

import org.codepenguin.labs.model.domain.VehicularRestrictionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Month.OCTOBER;
import static org.codepenguin.labs.model.enums.ResponseStatus.ERROR;
import static org.codepenguin.labs.model.enums.ResponseStatus.OK;
import static org.junit.jupiter.api.Assertions.*;

class VehicularRestrictionServiceTest {

    private VehicularRestrictionService service;

    @BeforeEach
    void setUp() {
        service = new VehicularRestrictionService();
    }

    @Test
    void evaluate1() {
        final var request = new VehicularRestrictionRequest(null);
        final var response = service.evaluate(request);

        assertNotNull(response);
        assertEquals(ERROR, response.status());
        assertEquals(request, response.request());
        assertNull(response.dateTime());
        assertNull(response.canDrive());
    }

    @Test
    void evaluate2() {
        final var request = new VehicularRestrictionRequest("ABC-DEF");
        final var response = service.evaluate(request);

        assertNotNull(response);
        assertEquals(ERROR, response.status());
        assertEquals(request, response.request());
        assertNull(response.dateTime());
        assertNull(response.canDrive());
    }


    @Test
    void evaluate3() {
        final var request = new VehicularRestrictionRequest("ABC-123");

        final var localDateTime = LocalDateTime.of(2022, OCTOBER, 15, 0, 0, 0);

        try (var localDateTimeMock = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(() -> LocalDateTime.now(ZoneId.of("America/Bogota")))
                    .thenReturn(localDateTime);

            final var response = service.evaluate(request);

            assertNotNull(response);
            assertEquals(OK, response.status());
            assertEquals(request, response.request());
            assertEquals(localDateTime, response.dateTime());
            assertTrue(response.canDrive());
        }
    }


    @Test
    void evaluate4() {
        final var request = new VehicularRestrictionRequest("ABC-123");

        final var localDateTime = LocalDateTime.of(2022, OCTOBER, 17, 4, 0, 0);

        try (var localDateTimeMock = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(() -> LocalDateTime.now(ZoneId.of("America/Bogota")))
                    .thenReturn(localDateTime);

            final var response = service.evaluate(request);

            assertNotNull(response);
            assertEquals(OK, response.status());
            assertEquals(request, response.request());
            assertEquals(localDateTime, response.dateTime());
            assertTrue(response.canDrive());
        }
    }

    @Test
    void evaluate5() {
        final var request = new VehicularRestrictionRequest("ABC-123");

        final var localDateTime = LocalDateTime.of(2022, OCTOBER, 17, 22, 0, 0);

        try (var localDateTimeMock = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(() -> LocalDateTime.now(ZoneId.of("America/Bogota")))
                    .thenReturn(localDateTime);

            final var response = service.evaluate(request);

            assertNotNull(response);
            assertEquals(OK, response.status());
            assertEquals(request, response.request());
            assertEquals(localDateTime, response.dateTime());
            assertTrue(response.canDrive());
        }
    }


    @Test
    void evaluate6() {
        final var request = new VehicularRestrictionRequest("ABC-123");

        final var localDateTime = LocalDateTime.of(2022, OCTOBER, 17, 12, 0, 0);

        try (var localDateTimeMock = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(() -> LocalDateTime.now(ZoneId.of("America/Bogota")))
                    .thenReturn(localDateTime);

            final var response = service.evaluate(request);

            assertNotNull(response);
            assertEquals(OK, response.status());
            assertEquals(request, response.request());
            assertEquals(localDateTime, response.dateTime());
            assertFalse(response.canDrive());
        }
    }


    @Test
    void evaluate7() {
        final var request = new VehicularRestrictionRequest("ABC-123");

        final var localDateTime = LocalDateTime.of(2022, OCTOBER, 18, 12, 0, 0);

        try (var localDateTimeMock = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(() -> LocalDateTime.now(ZoneId.of("America/Bogota")))
                    .thenReturn(localDateTime);

            final var response = service.evaluate(request);

            assertNotNull(response);
            assertEquals(OK, response.status());
            assertEquals(request, response.request());
            assertEquals(localDateTime, response.dateTime());
            assertTrue(response.canDrive());
        }
    }
}