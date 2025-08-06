package com.haddad.blogin.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Blogger {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bloggerId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "blogger")
    private List<Post> posts;


    public Blogger() {
    }
    public Blogger(Long userId, String username, String email, String password, Role role) {
        this.bloggerId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return bloggerId;
    }
    public void setUserId(Long userId) {
        this.bloggerId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
