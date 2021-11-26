package com.nasnav.snapshot.entities;

public enum UserRole {
    Admin {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    },
    User {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    }

}
