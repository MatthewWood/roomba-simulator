package com.matthew.requests.validators;

import com.matthew.requests.RoombaSimulateRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import static org.junit.Assert.*;

public class RoombaSimulateRequestValidatorTest {

    private RoombaSimulateRequestValidator validator;

    @Before
    public void setUp() {
        validator = new RoombaSimulateRequestValidator();
    }

    @Test
    public void roomSizeInvalid() {
        RoombaSimulateRequest roombaSimulateRequest = getRoombaSimulateRequest();
        roombaSimulateRequest.setRoomSize(null);
        BindException errors = new BindException(roombaSimulateRequest, "request");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("roomSize"));
        assertEquals("Invalid param", errors.getFieldError("roomSize").getCode());
    }

    @Test
    public void coordsInvalid() {
        RoombaSimulateRequest roombaSimulateRequest = getRoombaSimulateRequest();
        roombaSimulateRequest.setCoords(null);
        BindException errors = new BindException(roombaSimulateRequest, "request");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("coords"));
        assertEquals("Invalid param", errors.getFieldError("coords").getCode());
    }

    @Test
    public void patchesInvalid() {
        RoombaSimulateRequest roombaSimulateRequest = getRoombaSimulateRequest();
        roombaSimulateRequest.setPatches(null);
        BindException errors = new BindException(roombaSimulateRequest, "request");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("patches"));
        assertEquals("Invalid param", errors.getFieldError("patches").getCode());

        roombaSimulateRequest.setPatches(new int[][]{{1,1}, {1}});
        errors = new BindException(roombaSimulateRequest, "request");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("patches"));
        assertEquals("Invalid coord within param", errors.getFieldError("patches").getCode());
    }

    @Test
    public void instructionsInvalid() {
        RoombaSimulateRequest roombaSimulateRequest = getRoombaSimulateRequest();
        roombaSimulateRequest.setInstructions(null);
        BindException errors = new BindException(roombaSimulateRequest, "request");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("instructions"));
        assertEquals("Invalid param", errors.getFieldError("instructions").getCode());

        roombaSimulateRequest.setInstructions("");
        errors = new BindException(roombaSimulateRequest, "instructions");
        ValidationUtils.invokeValidator(validator, roombaSimulateRequest, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getFieldErrorCount("instructions"));
        assertEquals("Invalid param", errors.getFieldError("instructions").getCode());
    }

    private RoombaSimulateRequest getRoombaSimulateRequest() {
        RoombaSimulateRequest request = new RoombaSimulateRequest();
        request.setRoomSize(new int[]{5, 5});
        request.setCoords(new int[]{1, 2});
        request.setPatches(new int[][]{{1, 0}, {2, 2}, {2, 3}});
        request.setInstructions("NNESEESWNWW");

        return request;
    }
}