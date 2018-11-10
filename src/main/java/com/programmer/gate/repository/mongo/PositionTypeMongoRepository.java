package com.programmer.gate.repository.mongo;

import com.programmer.gate.model.mongo.PositionTypeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionTypeMongoRepository extends MongoRepository<PositionTypeMongo,String> {

}
