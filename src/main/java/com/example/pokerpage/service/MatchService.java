package com.example.pokerpage.service;

import com.example.pokerpage.dto.match.MatchCreateDTO;
import com.example.pokerpage.dto.match.MatchDTO;
import com.example.pokerpage.dto.match.MatchWithUsersDTO;
import com.example.pokerpage.dto.match_user.MatchUserAddPlayerDTO;
import com.example.pokerpage.dto.match_user.MatchUserDTO;
import com.example.pokerpage.dto.match_user.UserInMatchDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.enums.ErrorEnum;
import com.example.pokerpage.exception.ValidationException;
import com.example.pokerpage.models.Match;
import com.example.pokerpage.models.MatchUser;
import com.example.pokerpage.models.User;
import com.example.pokerpage.repository.MatchRepository;
import com.example.pokerpage.repository.MatchUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchUserService matchUserService;
    private final ObjectMapper objectMapper;

    public MatchDTO create(MatchCreateDTO matchCreateDTO){
        Match match = objectMapper.convertValue(matchCreateDTO, Match.class);

        Match createdMatch = matchRepository.save(match);

        List<UserInMatchDTO> users = matchCreateDTO.getUsers();

        this.addMatchUser(match, users);

        return objectMapper.convertValue(createdMatch, MatchDTO.class);
    }

    public List<MatchDTO> list(){
        return this.matchRepository.findAll().stream().map(match -> objectMapper.convertValue(match, MatchDTO.class)).collect(Collectors.toList());
    }

    public MatchDTO get(int id){
        Match match = this.matchRepository.getReferenceById(id);

        return this.objectMapper.convertValue(match, MatchDTO.class);
    }

    public void delete(int id){
        Match matchToBeDeleted = this.matchRepository.getReferenceById(id);

        this.matchRepository.delete(matchToBeDeleted);
    }

    public void addPlayer(MatchUserAddPlayerDTO matchUserAddPlayerDTO){
        Match match = this.getMatch(matchUserAddPlayerDTO.getMatchId());

        this.addMatchUser(match, matchUserAddPlayerDTO.getUsers());
    }

    public MatchWithUsersDTO getWithPlayers(int id){
        Match match = this.getMatch(id);

        MatchWithUsersDTO matchWithUsersDTO = this.objectMapper.convertValue(match, MatchWithUsersDTO.class);

        List<UserInMatchDTO> usersInMatch = this.matchUserService.getUsersInMatch(id);

        matchWithUsersDTO.setUsers(usersInMatch);

        return matchWithUsersDTO;
    }

    private void addMatchUser(Match match, List<UserInMatchDTO> users){
        float minimumBuyIn = match.getBuyIn();

        users.forEach(userInMatch -> {
            float buyIn = userInMatch.getTotalSpent();

            if(buyIn < minimumBuyIn){
                throw new ValidationException(ErrorEnum.ILLEGAL_BUY_IN.getMessage(), ErrorEnum.ILLEGAL_BUY_IN);
            }

            this.matchUserService.create(userInMatch, match);

            match.setTotal(match.getTotal() + buyIn);

            this.matchRepository.save(match);
        });
    }

    private Match getMatch(int id){
        Match match = this.matchRepository.findById(id);

        if(match == null) throw new ValidationException("Match not found", ErrorEnum.NOT_FOUND);

        return match;
    }
}
