package com.programmer.gate;

import com.programmer.gate.mapper.PlayerMapper;
import com.programmer.gate.model.Player;
import com.programmer.gate.model.Team;
import com.programmer.gate.repository.TeamRepository;
import com.programmer.gate.service.PlayerService;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.programmer.gate.service.SoccerService;

@SpringBootApplication
@EnableBatchProcessing
public class Application implements CommandLineRunner {

  Logger logger = LoggerFactory.getLogger(Application.class);

  @Autowired
  PlayerService playerService;

  @Autowired
  SoccerService soccerService;

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  PlayerMapper playerMapper;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  public void run(String... arg0) throws Exception {

    logger.info("Application RUN");

//    soccerService.addBarcelonaPlayer("Xavi Hernandez", "Midfielder", 6);

    List<String> players = soccerService.getAllTeamPlayers(1);
    for (String player : players) {
      System.out.println("Introducing Barca player => " + player);
    }

    List<Player> playerList = playerService.getAllPlayers();
    for (Player player : playerList) {
      System.out.println("Introducing Player => " + player);
    }

    List<Player> myBatisPlayer = new ArrayList<>();

    Team team = teamRepository.findOne(1L);

    logger.info("team : {}",team);


    for (int i = 1; i < 101; i++) {
      Player player = new Player();
      player.setId(Long.valueOf(100 + i));
      player.setName("yunus" + i);
      player.setNum(1);
      player.setPosition("poz" + i);
      player.setTeam(team);
      myBatisPlayer.add(player);
    }

    playerMapper.insertAll(myBatisPlayer);

  }
}