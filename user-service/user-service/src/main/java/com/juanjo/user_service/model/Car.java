package com.juanjo.user_service.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    public String brand;
    public String model;
    public int userId;
}
