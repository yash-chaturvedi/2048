package com.example.model;

import java.util.Objects;

/**
 * @author Yash Chaturvedi
 */
public class Tile {
    private final int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
