package com.example.pokerpage.service;

import com.example.pokerpage.dto.match_user.MatchUserDTO;
import com.example.pokerpage.dto.match_user.UserInMatchDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.models.Match;
import com.example.pokerpage.models.MatchUser;
import com.example.pokerpage.models.User;
import com.example.pokerpage.repository.MatchUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchUserService {
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final MatchUserRepository matchUserRepository;

    public MatchUserDTO create(UserInMatchDTO userInMatchDTO, Match match){
        UserDTO userDTO = this.userService.get(userInMatchDTO.getUsername());

        User user = this.objectMapper.convertValue(userDTO, User.class);

        float buyIn = userInMatchDTO.getTotalSpent();

        MatchUser matchUser = new MatchUser(user, match, buyIn);

        this.matchUserRepository.save(matchUser);

        return this.objectMapper.convertValue(matchUser, MatchUserDTO.class);
    }

    public List<UserInMatchDTO> getUsersInMatch(int matchId){
        List<MatchUser> matchUsers = this.matchUserRepository.findByMatchId(matchId);

        List<UserInMatchDTO> users = new ArrayList<>();

        for (MatchUser matchUser : matchUsers) {
            UserInMatchDTO userInMatchDTO = new UserInMatchDTO(matchUser.getUser().getNome(), matchUser.getTotal_spent());

            users.add(userInMatchDTO);
        }

        return users;
    }
}
