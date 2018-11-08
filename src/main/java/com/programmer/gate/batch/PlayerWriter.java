package com.programmer.gate.batch;

import com.programmer.gate.model.Player;
import com.programmer.gate.model.PlayerBatch;
import com.programmer.gate.repository.PlayerBatchRepository;
import com.programmer.gate.repository.PlayerRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerWriter implements ItemWriter<PlayerBatch> {

  @Autowired
  PlayerBatchRepository playerBatchRepository;

  @Autowired
  PlayerRepository playerRepository;

  @Override
  public void write(List<? extends PlayerBatch> list) throws Exception {
    for (PlayerBatch playerBatch : list) {
      playerBatchRepository.save(playerBatch);
      Player player =playerRepository.findOne(playerBatch.getPlayer().getId());
      player.setNum(2);
      playerRepository.save(player);
    }
  }

}
