package com.programmer.gate.batch;

import com.programmer.gate.Application;
import com.programmer.gate.model.Player;
import com.programmer.gate.model.PlayerBatch;
import com.programmer.gate.model.Team;
import com.programmer.gate.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerProcessor implements ItemProcessor<Player, PlayerBatch> {

  Logger logger = LoggerFactory.getLogger(PlayerProcessor.class);

  @Autowired
  private TeamService teamService;


  @Override
  public PlayerBatch process(Player data) throws Exception {

    logger.info("START PROCESS : {}",data.getName());

    PlayerBatch playerBatch = new PlayerBatch();
    playerBatch.setName(data.getName());
    playerBatch.setNum(data.getNum());
    playerBatch.setPlayer(data);
    playerBatch.setPosition(data.getPosition());
    Team team = teamService.findOne(data.getTeam().getId());
    playerBatch.setTeam(team);

    Thread.sleep(3600);

    logger.info("FINISH PROCESS : {}",data.getName());


    return playerBatch;
  }

}
