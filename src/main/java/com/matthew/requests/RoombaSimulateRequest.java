package com.matthew.requests;

import org.springframework.data.annotation.Id;

public class RoombaSimulateRequest {
    @Id
    private String id;
    private int[] roomSize;
    private int[] coords;
    private int[][] patches;
    private String instructions;

    public String getId() {
        return id;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int[] roomDimentions) {
        this.roomSize = roomDimentions;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public int[][] getPatches() {
        return patches;
    }

    public void setPatches(int[][] patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
