package com.programmer.gate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.programmer.gate.mapper.PlayerMapper;
import com.programmer.gate.model.Player;
import com.programmer.gate.model.Team;
import com.programmer.gate.repository.mongo.PlayerMongoRepository;
import com.programmer.gate.repository.TeamRepository;
import com.programmer.gate.repository.mongo.TeamMongoRepository;
import com.programmer.gate.repository.mongo.TeamTypeMongoRepository;
import com.programmer.gate.service.PlayerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.programmer.gate.service.SoccerService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableBatchProcessing
@EnableCaching
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

  @Autowired
  PlayerMongoRepository playerMongoRepository;

  @Autowired
  TeamMongoRepository teamMongoRepository;

  @Autowired
  TeamTypeMongoRepository teamTypeMongoRepository;

  @Autowired
  MongoTemplate mongoTemplate;

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

    Team team1 = teamRepository.findOne(1L);

    for (int i = 1; i < 101; i++) {
      Player player = new Player();
      player.setId(Long.valueOf(100 + i));
      player.setName("yunus" + i);
      player.setNum(1);
      player.setPosition("poz" + i);
      player.setTeam(team1);
      myBatisPlayer.add(player);
    }

    Team team2 = teamRepository.findOne(2L);


    for (int i = 1; i < 101; i++) {
      Player player = new Player();
      player.setId(Long.valueOf(200 + i));
      player.setName("ahmet" + i);
      player.setNum(1);
      player.setPosition("poz" + i);
      player.setTeam(team2);
      myBatisPlayer.add(player);
    }

    playerMapper.insertAll(myBatisPlayer);

    //MongoDB


    DB db =mongoTemplate.getDb();

    DBCollection collection = db.getCollection("teamDocument");

    BasicDBObject document = new BasicDBObject();
    document.put("team", "Besiktas");
    document.put("teamType", "football");

    BasicDBObject documentDetail = new BasicDBObject();
    documentDetail.put("records", 99);
    documentDetail.put("type", Arrays.asList("1","2","3"));
    documentDetail.put("active", "true");

    document.put("detail", documentDetail);

    collection.insert(document);


    BasicDBObject document2 = new BasicDBObject();
    document2.put("team", "Besiktas2");
    document2.put("teamType", "football2");

    BasicDBObject documentDetail2 = new BasicDBObject();
    documentDetail2.put("records", 99);
    documentDetail2.put("type", Arrays.asList("4","2","3","5"));
    documentDetail2.put("active", "true");

    document2.put("detail", documentDetail2);

    collection.insert(document2);


    List<String> list = collection.distinct("detail.type");

    for (String a:list) {

      System.out.println("result : " + a);
    }

    /*
    PositionTypeMongo positionTypeMongo = new PositionTypeMongo();




    List<PlayerMongo> list = new ArrayList<>();

    TeamMongo teamMongo = new TeamMongo();

    teamMongo.setTeamName("Besiktas");

    teamMongo.setTeamTypeMongo(new TeamTypeMongo("football","football"));

    for (Player player :playerService.getAllPlayers()) {

      PlayerMongo playerMongo = new PlayerMongo();
      playerMongo.setName(player.getName());
      playerMongo.setNum(player.getNum());
      playerMongo.setPositionTypeMongo(new PositionTypeMongo(player.getPosition(),player.getPosition()));
      list.add(playerMongo);
    }

    teamMongo.setPlayerMongoList(list);

    teamMongoRepository.save(teamMongo);

    List<PlayerMongo> playerMongoList= playerMongoRepository.findAll();

    for (PlayerMongo playerMongo :playerMongoList) {
      System.out.println("Introducing Player => " + playerMongo.getName());

    }

    TeamTypeMongo teamTypeMongo2 = new TeamTypeMongo("basketball","basketball");
    teamTypeMongoRepository.save(teamTypeMongo2);

    for (TeamTypeMongo teamTypeMongo :teamTypeMongoRepository.findAll()) {
      System.out.println("Introducing TeamTypeMongo => " + teamTypeMongo.getName());

    }*/





  }
}