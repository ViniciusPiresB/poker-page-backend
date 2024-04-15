package com.example.pokerpage.models;

import jakarta.persistence.*;

@Entity
@Table(name = "match_user")
@IdClass(UserMatchId.class)
public class MatchUser {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    private float total_spent;
}
