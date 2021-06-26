package com.test.human.resource.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.human.resource.api.model.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Min(value = 1, message = "The min value must be greater than 0")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    @NotBlank
    private String cellphone;

    @NotBlank
    private String cityName;

    public CandidateDTO(Candidate candidate) {
        this.id = candidate.getId();
        this.name = candidate.getName();
        this.lastName = candidate.getLastName();
        this.address = candidate.getAddress();
        this.cellphone = candidate.getCellphone();
        this.cityName = candidate.getCityName();
    }

}
