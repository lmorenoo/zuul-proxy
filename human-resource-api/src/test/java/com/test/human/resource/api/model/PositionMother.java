package com.test.human.resource.api.model;

import com.github.javafaker.Faker;
import com.test.human.resource.api.model.Position;

public class PositionMother {

  public static Position random() {
    return new Position(new Faker().company().profession());
  }
}
