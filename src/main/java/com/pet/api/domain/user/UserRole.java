package com.pet.api.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin");

    private final String role;

    UserRole(String role){
        this.role = role;
    }
}
