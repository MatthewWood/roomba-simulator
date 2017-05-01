package com.matthew.requests.validators;

import com.matthew.requests.RoombaSimulateRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoombaSimulateRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RoombaSimulateRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoombaSimulateRequest simulateRequest = (RoombaSimulateRequest) target;

        if (simulateRequest.getRoomSize() == null || simulateRequest.getRoomSize().length != 2) {
            errors.rejectValue("roomSize", "Invalid param");
        }
        if (simulateRequest.getCoords() == null || simulateRequest.getCoords().length != 2) {
            errors.rejectValue("coords", "Invalid param");
        }
        if (simulateRequest.getPatches() == null) {
            errors.rejectValue("patches", "Invalid param");
        } else {
            for (int[] patch : simulateRequest.getPatches()) {
                if (patch.length != 2) {
                    errors.rejectValue("patches", "Invalid coord within param");
                    break;
                }
            }
        }

        if (simulateRequest.getInstructions() == null || simulateRequest.getInstructions().isEmpty()) {
            errors.rejectValue("instructions", "Invalid param");
        }
    }
}
