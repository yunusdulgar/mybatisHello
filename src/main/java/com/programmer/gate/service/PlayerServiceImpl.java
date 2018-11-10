package com.programmer.gate.service;

import com.programmer.gate.model.Player;
import com.programmer.gate.repository.PlayerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PlayerServiceImpl implements PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> getAllPlayers() {
    List<Player> players = (List<Player>) playerRepository.findAll();

    return players;
  }

  public List<Player> getOnePlayer() {
    List<Player> players = (List<Player>) playerRepository.findByNum(1);
    return players;
  }

  public Player findOne(Long playerId) {
    return playerRepository.findOne(playerId);
  }




}
