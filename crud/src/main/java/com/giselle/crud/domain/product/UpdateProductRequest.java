package com.giselle.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotBlank(message = "O ID não pode estar em branco") String id,
        @NotBlank(message = "O nome não pode estar em branco") String name,
        @NotNull(message = "O preço em centavos não pode ser nulo") Integer price_in_cents) {
}