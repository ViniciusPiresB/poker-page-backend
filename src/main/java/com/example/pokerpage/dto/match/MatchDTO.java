package com.example.pokerpage.dto.match;

import com.example.pokerpage.models.MatchUser;
import com.example.pokerpage.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    private int id;

    private float buyIn;

    private float total;
}
