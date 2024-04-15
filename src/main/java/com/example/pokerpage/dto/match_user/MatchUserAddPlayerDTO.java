package com.example.pokerpage.dto.match_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchUserAddPlayerDTO {
    int matchId;

    String username;

    float buyIn;
}
