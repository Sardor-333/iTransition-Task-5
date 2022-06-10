package com.example.websocketgroupdemo.payload.req;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {

    @NotBlank(message = "Name should not be blank!")
    String name;
}
