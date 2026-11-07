package io.github.tarikiguezarane.miniswitch.iso8583;

/**
 * Versions of the ISO 8583 standard, identified by the first digit of the MTI
 * (Message Type Indicator). For example the MTI {@code 0100} starts with {@code 0},
 * meaning ISO 8583:1987, the version still used by most card networks.
 */
public enum Iso8583Version {
    V1987('0'),
    V1993('1'),
    V2003('2');

    private final char mtiDigit;

    Iso8583Version(char mtiDigit) {
        this.mtiDigit = mtiDigit;
    }

    /** Returns the digit that encodes this version in position 1 of the MTI. */
    public char mtiDigit() {
        return mtiDigit;
    }

    /**
     * Resolves the version from a 4-digit MTI.
     *
     * @param mti the message type indicator, e.g. {@code "0200"}
     * @return the matching version
     * @throws IllegalArgumentException if the MTI is not 4 digits or the version digit is unknown
     */
    public static Iso8583Version fromMti(String mti) {
        if (mti == null || !mti.matches("\\d{4}")) {
            throw new IllegalArgumentException("MTI must be exactly 4 digits: " + mti);
        }
        char digit = mti.charAt(0);
        for (Iso8583Version version : values()) {
            if (version.mtiDigit == digit) {
                return version;
            }
        }
        throw new IllegalArgumentException("Unsupported ISO 8583 version digit: " + digit);
    }
}
