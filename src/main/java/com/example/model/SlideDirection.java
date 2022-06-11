package com.example.model;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yash Chaturvedi
 */
public enum SlideDirection {
    LEFT(0), RIGHT(1), UP(2), DOWN(3);

    private final int direction;
    private static final Map<Integer, SlideDirection> map = new HashMap<>();

    SlideDirection(int direction) {
        this.direction = direction;
    }

    static {
        for(SlideDirection sd : SlideDirection.values()) {
            map.put(sd.getDirection(), sd);
        }
    }

    public static SlideDirection valueOf(int direction) {
        if(map.containsKey(direction)) {
            return map.get(direction);
        }
        else {
            throw new InvalidParameterException("Invalid Input");
        }
    }

    public int getDirection() {
        return direction;
    }
}
