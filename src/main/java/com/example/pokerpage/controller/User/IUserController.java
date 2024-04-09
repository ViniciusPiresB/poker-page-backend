package com.example.pokerpage.controller.User;

import com.example.pokerpage.dto.user.UserCreateDTO;
import com.example.pokerpage.dto.user.UserDTO;
import com.example.pokerpage.dto.user.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserController {
    @Operation(summary = "Create user", description = "Create a user in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Creates a user"),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO);

    @Operation(summary = "List all users", description = "List all users from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists all users"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Users not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping
    ResponseEntity<List<UserDTO>> list();

    @Operation(summary = "List a user by name", description = "List only one user from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Get a user"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping("/{name}")
    ResponseEntity<UserDTO> get(@PathVariable String name);

    @Operation(summary = "Update user", description = "Update a user in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Updates a user"),
                    @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PatchMapping
    ResponseEntity<UserDTO> update(@RequestBody UserUpdateDTO userUpdateDTO);

    @Operation(summary = "Delete a user by name", description = "Delete only one user from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deletes a user"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "500", description = "An exception occurred", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    ResponseEntity<Void> delete(@PathVariable String nome);
}
