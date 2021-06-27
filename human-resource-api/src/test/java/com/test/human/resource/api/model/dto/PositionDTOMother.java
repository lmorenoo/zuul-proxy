package com.test.human.resource.api.model.dto;

import com.github.javafaker.Faker;

public class PositionDTOMother {

  public static PositionDTO random() {
    var faker = new Faker();
    var dto = new PositionDTO(faker.number().randomNumber(),
      faker.company().profession());
    return dto;
  }
}
