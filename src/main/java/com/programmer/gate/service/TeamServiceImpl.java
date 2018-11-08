package com.programmer.gate.service;

import com.programmer.gate.model.Team;
import com.programmer.gate.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

  Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

  @Autowired
  private TeamRepository teamRepository;

  @Override
  public Team findOne(Long teamId){

   // logger.error("!!!!!!! findOne : {}" ,teamId);
    for (int i = 0; i < 10000 ; i++) {


    }

    return teamRepository.findOne(teamId);

  }

}
