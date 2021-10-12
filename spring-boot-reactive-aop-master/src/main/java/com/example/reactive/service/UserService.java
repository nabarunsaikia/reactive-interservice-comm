package com.example.reactive.service;

import com.example.reactive.model.User;
import com.example.reactive.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Octavian Krody, octavian.krody@sap.com
 * @since 10/07/2018
 */
@Service
public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {this.repository = repository;}

  public Mono<List<User>> all() {
    System.out.println("Service");
    return Mono.just(new ArrayList<>());
    //return repository.all().collectList();
  }
}
