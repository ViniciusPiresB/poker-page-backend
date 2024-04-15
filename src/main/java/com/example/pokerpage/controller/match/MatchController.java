package com.example.pokerpage.controller.match;

import com.example.pokerpage.dto.match.MatchCreateDTO;
import com.example.pokerpage.dto.match.MatchDTO;
import com.example.pokerpage.dto.match_user.MatchUserAddPlayerDTO;
import com.example.pokerpage.service.MatchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/match")
@Tag(name = "Match")
public class MatchController implements IMatchController {
    private final MatchService matchService;

    @PostMapping
    @Transactional
    public ResponseEntity<MatchDTO> create(@RequestBody MatchCreateDTO matchCreateDTO){
        return new ResponseEntity<>(this.matchService.create(matchCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> list(){
        return new ResponseEntity<>(this.matchService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> get(@PathVariable int id){
        return new ResponseEntity<>(this.matchService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        this.matchService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PatchMapping("/add_player")
    public ResponseEntity<Void> addPlayer(@RequestBody MatchUserAddPlayerDTO matchUserAddPlayerDTO){
        this.matchService.addPlayer(matchUserAddPlayerDTO);
        return ResponseEntity.ok().build();
    }
}
