package com.example.pokerpage.repository;

import com.example.pokerpage.models.MatchUser;
import com.example.pokerpage.models.UserMatchId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchUserRepository extends JpaRepository<MatchUser, UserMatchId> {
}
