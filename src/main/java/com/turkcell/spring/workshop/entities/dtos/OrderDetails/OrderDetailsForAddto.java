package com.turkcell.spring.workshop.entities.dtos.OrderDetails;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsForAddto {

    private int productId;

    @Min(1)
    private short quantity;

}
