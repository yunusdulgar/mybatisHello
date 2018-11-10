package com.programmer.gate.repository.mongo;

import com.programmer.gate.model.mongo.TeamTypeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamTypeMongoRepository extends MongoRepository<TeamTypeMongo,String> {



}