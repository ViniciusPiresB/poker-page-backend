package com.example.pokerpage.dto.match_user;

import com.example.pokerpage.dto.match.MatchDTO;
import com.example.pokerpage.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchUserDTO {
    UserDTO user;
    MatchDTO match;
    float totalSpent;
}
