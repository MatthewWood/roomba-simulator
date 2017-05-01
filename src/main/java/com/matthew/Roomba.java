package com.matthew;

import java.awt.*;

class Roomba {
    private Point currentPosition;
    private int dirtPatchesCleanedCount;

    Roomba() {
        dirtPatchesCleanedCount = 0;
    }

    Point getCurrentPosition() {
        return currentPosition;
    }

    void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    int getDirtPatchesCleanedCount() {
        return dirtPatchesCleanedCount;
    }

    void increaseDirtCleanedCount() {
        dirtPatchesCleanedCount += 1;
    }

    void moveNorth() {
        currentPosition.y += 1;
    }

    void moveEast() {
        currentPosition.x += 1;
    }

    void moveSouth() {
        currentPosition.y -= 1;
    }

    void moveWest() {
        currentPosition.x -= 1;
    }
}
