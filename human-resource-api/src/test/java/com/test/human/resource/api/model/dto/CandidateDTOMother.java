package com.test.human.resource.api.model.dto;

import com.github.javafaker.Faker;

public class CandidateDTOMother {

  public static CandidateDTO random() {
    var faker = new Faker();
    var dto = new CandidateDTO(faker.number().randomNumber(),
      faker.dragonBall().character(),
      faker.funnyName().name(),
      faker.address().fullAddress(),
      faker.phoneNumber().cellPhone(),
      faker.address().cityName());
    return dto;
  }
}
