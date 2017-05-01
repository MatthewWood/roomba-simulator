package com.matthew.controllers.impl;

import com.matthew.Simulation;
import com.matthew.controllers.SimulationController;
import com.matthew.repositories.RequestRepository;
import com.matthew.repositories.ResponseRepository;
import com.matthew.requests.RoombaSimulateRequest;
import com.matthew.requests.validators.RoombaSimulateRequestValidator;
import com.matthew.responses.RoombaSimulateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SimulationControllerImpl implements SimulationController {

    private RequestRepository requestRepository;
    private ResponseRepository responseRepository;
    private Simulation simulation;

    @Autowired
    public SimulationControllerImpl(RequestRepository requestRepository, ResponseRepository responseRepository,
                                    Simulation simulation) {
        this.requestRepository = requestRepository;
        this.responseRepository = responseRepository;
        this.simulation = simulation;
    }

    @Override
    public ResponseEntity<RoombaSimulateResponse> simulateRoombaClean(@RequestBody RoombaSimulateRequest request, BindingResult result) {
        RoombaSimulateRequestValidator validator = new RoombaSimulateRequestValidator();
        validator.validate(request, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // run the simulation
        RoombaSimulateResponse response;
        simulation.createRoom(request.getRoomSize()[0], request.getRoomSize()[1], request.getPatches());
        response = simulation.run(request.getCoords(), request.getInstructions());

        // persist the request and response
        RoombaSimulateRequest requestFromSave = requestRepository.save(request);
        response.setRequestId(requestFromSave.getId());
        responseRepository.save(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
