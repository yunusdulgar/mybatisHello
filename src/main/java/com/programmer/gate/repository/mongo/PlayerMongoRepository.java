package com.programmer.gate.repository.mongo;

import com.programmer.gate.model.mongo.PlayerMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMongoRepository extends MongoRepository<PlayerMongo,String> {



}
