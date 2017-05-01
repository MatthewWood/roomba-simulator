package com.matthew.repositories;

import com.matthew.responses.RoombaSimulateResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<RoombaSimulateResponse, String> {
}
