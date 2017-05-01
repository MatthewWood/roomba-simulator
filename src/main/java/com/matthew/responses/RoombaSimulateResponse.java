package com.matthew.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

public class RoombaSimulateResponse {
    @Id
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String requestId;
    private int[] coords;
    private int patches;

    public String getId() {
        return id;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }
}
