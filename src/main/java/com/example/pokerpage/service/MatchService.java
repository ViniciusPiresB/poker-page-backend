package com.example.pokerpage.service;

import com.example.pokerpage.dto.match.MatchCreateDTO;
import com.example.pokerpage.dto.match.MatchDTO;
import com.example.pokerpage.dto.match_user.MatchUserAddPlayerDTO;
import com.example.pokerpage.dto.user.UserDTO;
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
    private final MatchUserRepository matchUserRepository;
    private final UserService userService;
    private final ObjectMapper objectMapper;



    public MatchDTO create(MatchCreateDTO matchCreateDTO){
        Match match = objectMapper.convertValue(matchCreateDTO, Match.class);
        Match createdMatch = matchRepository.save(match);
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
        String username = matchUserAddPlayerDTO.getUsername();
        int matchId = matchUserAddPlayerDTO.getMatchId();
        float buyIn = matchUserAddPlayerDTO.getBuyIn();

        UserDTO userDTO = this.userService.get(username);

        User user = this.objectMapper.convertValue(userDTO, User.class);

        Match match = this.matchRepository.getReferenceById(matchId);

        float minimumBuyIn = match.getBuyIn();

        if(buyIn < minimumBuyIn){
            throw new ValidationException("ERRO: Buy in é menor que o mínimo da partida");
        }

        MatchUser matchUser = new MatchUser(user, match, buyIn);

        this.matchUserRepository.save(matchUser);
    }
}
