package com.example.passwordkeeper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "passwordkeeper")
public class PasswordKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String website;

    private String userName;

    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    private User user;


}
