package com.example.pokerpage.dto.match_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInMatchDTO {
    String username;
    float totalSpent;
}
