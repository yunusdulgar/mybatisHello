package com.programmer.gate.batch;

import com.programmer.gate.model.Player;
import com.programmer.gate.repository.PlayerRepository;
import com.programmer.gate.service.PlayerService;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class PlayerReader implements ItemReader<Player> {

  Logger logger = LoggerFactory.getLogger(PlayerProcessor.class);

  @Autowired
  public PlayerService playerService;

  private volatile AtomicInteger nextPlayerIndex = new AtomicInteger(0);
  private volatile List<Player> playerData;

  private synchronized void initialize() {
    if (playerData == null) {
      playerData = playerService.getOnePlayer();
      logger.info("INITIALIZE playerData size  {}", playerData.size());
    }
  }

  @Override
  public synchronized Player read() throws Exception {

    if (playerData == null) {
      logger.info("FINISIH playerData == null");
      initialize();
      if (playerData == null) {
        return null;
      }
    }

    if (playerData != null && playerData.isEmpty()) {
      logger.info("FINISIH2 playerData == null");
      playerData = null;
      return null;
    }

    Player nextPlayer = null;

    if (nextPlayerIndex.get() < playerData.size()) {
      nextPlayer = playerData.get(nextPlayerIndex.get());
      nextPlayerIndex.getAndIncrement();
    }

    if (nextPlayer == null) {
      logger.info("2playerData = null");
      nextPlayerIndex.set(0);
      playerData.clear();
      return null;
    }

    logger.info("return nextPlayer : {}", nextPlayer != null ? nextPlayer.getName() : null);
    return nextPlayer;
  }


}