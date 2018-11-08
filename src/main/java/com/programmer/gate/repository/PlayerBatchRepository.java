package com.programmer.gate.repository;

import com.programmer.gate.model.PlayerBatch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerBatchRepository extends CrudRepository<PlayerBatch, Long> {

}
