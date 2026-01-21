package com.java_inaction.graphic;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Point {
    private final int x;
    private final int y;

    public Point moveRightBy(int amount) {
        return new Point(this.x + amount, this.y);
    }
}
