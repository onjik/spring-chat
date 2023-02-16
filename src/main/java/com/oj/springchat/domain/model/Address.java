package com.oj.springchat.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Embeddable
public class Address {

    @NotEmpty
    @Column(name="county")
    private String county;

    @NotEmpty
    @Column(name = "state")
    private String state;

    @NotEmpty
    @Column(name = "city")
    private String city;

    @NotEmpty
    @Digits(integer = 9, fraction = 0)
    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Builder
    public Address(String county, String state, String city, String zipCode) {
        this.county = county;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }
}
