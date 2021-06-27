package com.test.human.resource.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.human.resource.api.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PositionDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotBlank
  private String name;

  public PositionDTO(Position position) {
    this.id = position.getId();
    this.name = position.getName();
  }
}
