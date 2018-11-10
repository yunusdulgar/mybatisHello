package com.programmer.gate.model.mongo;

import java.util.List;

public class TeamMongo {

  private String teamName;
  private List<PlayerMongo> playerMongoList;
  private TeamTypeMongo teamTypeMongo;


  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public List<PlayerMongo> getPlayerMongoList() {
    return playerMongoList;
  }

  public void setPlayerMongoList(
      List<PlayerMongo> playerMongoList) {
    this.playerMongoList = playerMongoList;
  }

  public TeamTypeMongo getTeamTypeMongo() {
    return teamTypeMongo;
  }

  public void setTeamTypeMongo(TeamTypeMongo teamTypeMongo) {
    this.teamTypeMongo = teamTypeMongo;
  }
}
