package com.test.human.resource.api.model;

import com.github.javafaker.Faker;

public class CandidateMother {

  public static Candidate random() {
    var faker = new Faker();
    var candidate = new Candidate(faker.number().randomNumber(),
      faker.dragonBall().character(),
      faker.funnyName().name(),
      faker.address().fullAddress(),
      faker.phoneNumber().cellPhone(),
      faker.address().cityName());
    return candidate;
  }
}
