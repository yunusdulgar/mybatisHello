package com.programmer.gate.service;

import com.programmer.gate.model.Player;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface PlayerService {

  List<Player> getAllPlayers();

  List<Player> getOnePlayer();

  Player findOne(Long id);
}
