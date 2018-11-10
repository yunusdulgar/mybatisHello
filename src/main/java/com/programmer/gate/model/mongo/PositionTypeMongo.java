package com.programmer.gate.model.mongo;

public class PositionTypeMongo {

  private String name;
  private String enumValue;

  public PositionTypeMongo(String name, String enumValue) {
    this.name = name;
    this.enumValue = enumValue;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEnumValue() {
    return enumValue;
  }

  public void setEnumValue(String enumValue) {
    this.enumValue = enumValue;
  }

}
