package com.outdoorsy.challenge;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.outdoorsy")
@EnableJpaRepositories(basePackages = "com.outdoorsy")
@SpringBootApplication
@ComponentScan(basePackages = "com.outdoorsy")
public class App {

  public static void main(String[] args) {
    run(App.class, args);
  }
}
