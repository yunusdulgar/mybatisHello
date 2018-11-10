package com.programmer.gate.service;

import com.programmer.gate.model.Team;
import com.programmer.gate.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

  Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

  @Autowired
  private TeamRepository teamRepository;

  @Override
  @Cacheable("teams")
  public Team findOne(Long teamId){

   logger.error("!!!!!!! findOne : {}" ,teamId);
    return teamRepository.findOne(teamId);

  }

}
