package com.matthew;

import com.matthew.responses.RoombaSimulateResponse;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class Simulation {
    private int[][] room;
    private int roomWidth;
    private int roomHeight;

    public void createRoom(int width, int height, int[][] dirtPatches) {
        // initialize the room
        room = new int[width][height];
        for (int x = width - 1; x >= 0; x--) {
            for (int y = 0; y < height; y++) {
                room[x][y] = 0;
            }
        }

        // add the dirt patches
        for (int[] dirtPatch : dirtPatches) {
            room[dirtPatch[0]][dirtPatch[1]] = 1;
        }

        roomWidth = width;
        roomHeight = height;
    }

    public RoombaSimulateResponse run(int[] startPoint, String instructions) {
        char[] instructionArr = instructions.toCharArray();
        Roomba roomba = new Roomba();
        roomba.setCurrentPosition(new Point(startPoint[0], startPoint[1]));

        for (char singleInstruction : instructionArr) {
            switch (singleInstruction) {
                case 'N':
                    if (roomba.getCurrentPosition().y < roomHeight - 1) {
                        roomba.moveNorth();
                    }
                    break;
                case 'E':
                    if (roomba.getCurrentPosition().x < roomWidth - 1) {
                        roomba.moveEast();
                    }
                    break;
                case 'S':
                    if (roomba.getCurrentPosition().y > 0) {
                        roomba.moveSouth();
                    }
                    break;
                case 'W':
                    if (roomba.getCurrentPosition().x > 0) {
                        roomba.moveWest();
                    }
                    break;
                default:
                    System.out.println("Unrecognised instruction " + singleInstruction + ", ignoring.");
            }

            if (room[roomba.getCurrentPosition().x][roomba.getCurrentPosition().y] == 1) {
                roomba.increaseDirtCleanedCount();
                room[roomba.getCurrentPosition().x][roomba.getCurrentPosition().y] = 0;
            }
        }

        RoombaSimulateResponse response = new RoombaSimulateResponse();
        response.setCoords(new int[]{roomba.getCurrentPosition().x, roomba.getCurrentPosition().y});
        response.setPatches(roomba.getDirtPatchesCleanedCount());

        return response;
    }

    int[][] getRoom() {
        return room;
    }
}
