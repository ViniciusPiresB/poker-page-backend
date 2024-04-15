package com.example.pokerpage.repository;

import com.example.pokerpage.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
