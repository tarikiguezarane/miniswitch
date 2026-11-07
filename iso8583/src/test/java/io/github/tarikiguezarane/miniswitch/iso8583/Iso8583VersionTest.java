package io.github.tarikiguezarane.miniswitch.iso8583;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class Iso8583VersionTest {

    @ParameterizedTest
    @CsvSource({
        "0100, V1987", // authorisation request
        "0210, V1987", // financial response
        "1200, V1993",
        "2800, V2003"
    })
    void resolvesVersionFromMti(String mti, Iso8583Version expected) {
        assertEquals(expected, Iso8583Version.fromMti(mti));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "010", "01000", "01A0", "9100"})
    void rejectsInvalidMti(String mti) {
        assertThrows(IllegalArgumentException.class, () -> Iso8583Version.fromMti(mti));
    }

    @Test
    void rejectsNullMti() {
        assertThrows(IllegalArgumentException.class, () -> Iso8583Version.fromMti(null));
    }

    @Test
    void exposesMtiDigit() {
        assertEquals('0', Iso8583Version.V1987.mtiDigit());
    }
}
