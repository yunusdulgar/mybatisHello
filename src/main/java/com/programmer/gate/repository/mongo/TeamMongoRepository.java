package com.programmer.gate.repository.mongo;

import com.programmer.gate.model.mongo.TeamMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMongoRepository extends MongoRepository<TeamMongo,String> {



}
