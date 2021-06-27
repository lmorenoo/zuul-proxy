package com.test.human.resource.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Candidate implements Serializable {

  @Id
  private Long id;

  private String name;

  private String lastName;

  private String address;

  private String cellphone;

  private String cityName;

}
