package com.programmer.gate.batch;

import com.programmer.gate.model.Player;
import com.programmer.gate.model.PlayerBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class PlayerBatchConfig {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Bean
  TaskExecutor playerTaskExecutor(){

    SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("yunus");
    asyncTaskExecutor.setConcurrencyLimit(10);
    return asyncTaskExecutor;
  }

  @Bean
  public Job processJob() {
    return jobBuilderFactory.get("processJob")
        .incrementer(new RunIdIncrementer()).listener(listener())
        .flow(orderStep1()).end().build();
  }

  @Bean
  public Step orderStep1() {
    return stepBuilderFactory.get("orderStep1").<Player, PlayerBatch>chunk(1)
        .reader(playerItemReader()).processor(playerItemProcessor())
        .writer(playerItemWriter()).taskExecutor(playerTaskExecutor()).throttleLimit(10).build();
  }

  @Bean
  public JobExecutionListener listener() {
    return new JobCompletionListener();
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  ItemReader<Player> playerItemReader(){
    PlayerReader playerReader = new PlayerReader();
    return playerReader;
  }

  @Bean
  ItemProcessor<Player,PlayerBatch> playerItemProcessor(){
    ItemProcessor itemProcessor = new PlayerProcessor();
    return itemProcessor;
  }

  @Bean
  ItemWriter<PlayerBatch> playerItemWriter(){
    PlayerWriter playerWriter = new PlayerWriter();
    return playerWriter;
  }

}
