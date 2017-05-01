package com.matthew.repositories;

import com.matthew.requests.RoombaSimulateRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<RoombaSimulateRequest, String> {
}