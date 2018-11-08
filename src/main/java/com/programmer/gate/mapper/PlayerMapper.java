package com.programmer.gate.mapper;

import com.programmer.gate.model.Player;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerMapper {

  void insertAll(List<Player> playerList);

}
