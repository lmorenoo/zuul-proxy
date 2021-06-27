package com.test.human.resource.api.model.dto;

import com.github.javafaker.Faker;

public class EmployeeDTOMother {

  public static EmployeeDTO random(Long personId, String positionName) {
    var faker = new Faker();
    var dto = new EmployeeDTO(faker.number().randomNumber(),
      personId,
      positionName,
      faker.number().randomDouble(2, 0, 1000));
    return dto;
  }

  public static EmployeeDTO random(String positionName) {
    return random(new Faker().number().randomNumber(), positionName);
  }

  public static EmployeeDTO random(Long personId) {
    return random(personId, new Faker().company().profession());
  }

  public static EmployeeDTO random() {
    var faker = new Faker();
    return random(faker.number().randomNumber(), faker.company().profession());

  }
}
