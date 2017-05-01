package com.matthew;

import com.matthew.responses.RoombaSimulateResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SimulationTest {

    private Simulation simulation;
    private int width;
    private int height;

    @Before
    public void before() {
        width = 5;
        height = 5;
        simulation = new Simulation();
        simulation.createRoom(width, height, new int[][]{{1, 0}, {2, 2}, {2, 3}});
    }

    @Test
    public void shouldCreateRoom() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i == 1 && j == 0) || (i == 2 && j == 2) || (i == 2 && j == 3)) {
                    assertEquals(1, simulation.getRoom()[i][j]);
                } else {
                    assertEquals(0, simulation.getRoom()[i][j]);
                }
            }
        }
    }

    @Test
    public void shouldNotMoveIfWall() {
        RoombaSimulateResponse response1 = simulation.run(new int[]{0, 0}, "S");
        assertArrayEquals(new int[]{0, 0}, response1.getCoords());

        RoombaSimulateResponse response2 = simulation.run(new int[]{0, 0}, "W");
        assertArrayEquals(new int[]{0, 0}, response2.getCoords());
    }

    @Test
    public void shouldMoveCorrectly() {
        RoombaSimulateResponse response1 = simulation.run(new int[]{0, 0}, "N");
        assertArrayEquals(new int[]{0, 1}, response1.getCoords());

        RoombaSimulateResponse response2 = simulation.run(new int[]{0, 0}, "E");
        assertArrayEquals(new int[]{1, 0}, response2.getCoords());
    }

    @Test
    public void shouldFollowInstructions() {
        RoombaSimulateResponse response1 = simulation.run(new int[]{0, 0}, "NNEEWS");
        assertArrayEquals(new int[]{1, 1}, response1.getCoords());

        RoombaSimulateResponse response2 = simulation.run(new int[]{0, 0}, "SSSSSSEEEEEEW");
        assertArrayEquals(new int[]{3, 0}, response2.getCoords());
    }

    @Test
    public void shouldIgnoreInvalidInstructions() {
        RoombaSimulateResponse response2 = simulation.run(new int[]{0, 0}, "SSSSSSEEPEEEEW");
        assertArrayEquals(new int[]{3, 0}, response2.getCoords());
    }

    @Test
    public void shouldCleanADirtPatch() {
        assertEquals(1, simulation.getRoom()[1][0]);
        RoombaSimulateResponse response2 = simulation.run(new int[]{0, 0}, "E");
        assertArrayEquals(new int[]{1, 0}, response2.getCoords());
        assertEquals(1, response2.getPatches());
        assertEquals(0, simulation.getRoom()[1][0]);
    }

}