package com.adminconsole.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "T_USER")
class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    User() {

    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }
}
