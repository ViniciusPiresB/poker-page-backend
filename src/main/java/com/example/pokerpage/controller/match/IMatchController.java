package com.example.pokerpage.controller.match;

import com.example.pokerpage.dto.match.MatchCreateDTO;
import com.example.pokerpage.dto.match.MatchDTO;
import com.example.pokerpage.dto.match_user.MatchUserAddPlayerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IMatchController {
    @Operation(summary = "Create match", description = "Create a match in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Creates a match"),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PostMapping
    ResponseEntity<MatchDTO> create(@RequestBody MatchCreateDTO matchCreateDTO);

    @Operation(summary = "List all matches", description = "List all matches from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists all matches"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Matches not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping
    ResponseEntity<List<MatchDTO>> list();

    @Operation(summary = "List a match by id", description = "List only one match from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Get a match"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<MatchDTO> get(@PathVariable int id);

    @Operation(summary = "Delete a match by name", description = "Delete only one match from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deletes a match"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> delete(@PathVariable int id);

    @Operation(summary = "Add a player in match", description = "Add one player in match with buy-in")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Player added to match"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Match or player not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "428", description = "Buy in is less than minimum", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> addPlayer(@RequestBody MatchUserAddPlayerDTO matchUserAddPlayerDTO);
}
