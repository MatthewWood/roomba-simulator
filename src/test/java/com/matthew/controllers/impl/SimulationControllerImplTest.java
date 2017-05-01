package com.matthew.controllers.impl;

import com.matthew.Simulation;
import com.matthew.repositories.RequestRepository;
import com.matthew.repositories.ResponseRepository;
import com.matthew.requests.RoombaSimulateRequest;
import com.matthew.responses.RoombaSimulateResponse;
import org.junit.Test;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class SimulationControllerImplTest {
    @Test
    public void shouldRunNormally() throws Exception {
        RequestRepository requestRepository = mock(RequestRepository.class);
        ResponseRepository responseRepository = mock(ResponseRepository.class);
        Simulation simulation = mock(Simulation.class);

        RoombaSimulateRequest request = getRoombaSimulateRequest();
        RoombaSimulateResponse response = getRoombaSimulateResponse();
        when(requestRepository.save((RoombaSimulateRequest) any())).thenReturn(request);
        when(simulation.run((int[]) any(), anyString())).thenReturn(response);

        SimulationControllerImpl controller = new SimulationControllerImpl(requestRepository, responseRepository, simulation);
        controller.simulateRoombaClean(request, mock(BindingResult.class));

        verify(simulation).createRoom(5, 5, request.getPatches());
        verify(simulation).run(new int[]{1, 2}, "NNESEESWNWW");
        verify(requestRepository).save(request);
        verify(responseRepository).save(response);

    }

    private RoombaSimulateRequest getRoombaSimulateRequest() {
        RoombaSimulateRequest request = new RoombaSimulateRequest();
        request.setRoomSize(new int[]{5, 5});
        request.setCoords(new int[]{1, 2});
        request.setPatches(new int[][]{{1, 0}, {2, 2}, {2, 3}});
        request.setInstructions("NNESEESWNWW");

        return request;
    }

    private RoombaSimulateResponse getRoombaSimulateResponse() {
        RoombaSimulateResponse response = new RoombaSimulateResponse();
        response.setRequestId("123456789");
        response.setCoords(new int[]{1, 2});
        response.setPatches(1);

        return response;
    }

}