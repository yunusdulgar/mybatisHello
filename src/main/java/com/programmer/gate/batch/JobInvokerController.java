package com.programmer.gate.batch;

import com.programmer.gate.model.Player;
import com.programmer.gate.repository.PlayerBatchRepository;
import com.programmer.gate.repository.PlayerRepository;
import com.programmer.gate.service.PlayerService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  Job processJob;

  @Autowired
  PlayerBatchRepository playerBatchRepository;

  @Autowired
  PlayerService playerService;


  @RequestMapping("/invokejob")
  public String handle() throws Exception {

    playerBatchRepository.deleteAll();

    JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
        .toJobParameters();
    jobLauncher.run(processJob, jobParameters);

    return "Batch job has been invoked";
  }
}
