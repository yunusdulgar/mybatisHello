package com.programmer.gate.model.mongo;

public class PlayerMongo {

  private String name;

  private int num;

  private PositionTypeMongo positionTypeMongo;


  public PlayerMongo() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }


  public PositionTypeMongo getPositionTypeMongo() {
    return positionTypeMongo;
  }

  public void setPositionTypeMongo(PositionTypeMongo positionTypeMongo) {
    this.positionTypeMongo = positionTypeMongo;
  }
}
