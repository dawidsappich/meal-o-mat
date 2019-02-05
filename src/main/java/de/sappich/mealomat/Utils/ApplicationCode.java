package de.sappich.mealomat.Utils;


public enum ApplicationCode {
    OK(1),
    UNKNOWN(-99),
    ERROR(9);

    private final int level;

    ApplicationCode(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
