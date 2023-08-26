package com.thinhlh.customer;

public record CustomerRequest(
        String firstName,
        String lastName,
        String email
) {
}
