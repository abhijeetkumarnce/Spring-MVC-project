package com.week2.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentDTO {

    Long id;

    @NotBlank(message = "Title of the department cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in title should be in the range: [3, 10]")
    String title;

    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    Boolean isActive;

    @PastOrPresent(message = "Creation date of department cannot be in the future")
    LocalDate createdAt;
}
