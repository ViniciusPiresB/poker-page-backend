package com.example.pokerpage.dto.match;

import com.example.pokerpage.dto.match_user.UserInMatchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchCreateDTO {
    private float buyIn;

    List<UserInMatchDTO> users;
}
