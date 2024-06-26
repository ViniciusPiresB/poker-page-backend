package com.example.pokerpage.dto.match_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchUserAddPlayerDTO {
    int matchId;

    List<UserInMatchDTO> users;
}
