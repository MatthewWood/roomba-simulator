package com.matthew.controllers;

import com.matthew.requests.RoombaSimulateRequest;
import com.matthew.responses.RoombaSimulateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SimulationController {

    @RequestMapping(path = "/simulation", method = RequestMethod.POST)
    public ResponseEntity<RoombaSimulateResponse> simulateRoombaClean(@RequestBody RoombaSimulateRequest request, BindingResult result);
}
