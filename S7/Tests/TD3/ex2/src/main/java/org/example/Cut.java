package org.example;

public enum Cut {
    SMALL_CUT(1), BIG_CUT(40);

    private final int value;

    Cut(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
