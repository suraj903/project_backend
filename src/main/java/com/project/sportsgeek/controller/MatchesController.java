package com.project.sportsgeek.controller;

import com.project.sportsgeek.exception.ResultException;
import com.project.sportsgeek.model.Matches;
import com.project.sportsgeek.model.Venue;
import com.project.sportsgeek.response.Result;
import com.project.sportsgeek.service.MatchesService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping(path = "/matches",produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchesController {

    @Autowired
    MatchesService matchesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Venue.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<Matches>>> getAllMatches() {
        Result<List<Matches>> matchesList = matchesService.findAllMatches();
        return new ResponseEntity<>(matchesList, HttpStatus.valueOf(matchesList.getCode()));
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Venue.class),
                    @ApiResponse(code = 404, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Matches>> getMatchesById(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int id) throws Exception {
        Result<Matches> matchesList = matchesService.findMatchesById(id);
        return new ResponseEntity<>(matchesList, HttpStatus.valueOf(matchesList.getCode()));
    }
    @GetMapping(value = "/oldMatches",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "success", response = Venue.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<List<Matches>>> findAllMatchesByPreviousDateAndResultStatus() {
        Result<List<Matches>> matchesList = matchesService.findAllMatchesByPreviousDateAndResultStatus();
        return new ResponseEntity<>(matchesList, HttpStatus.valueOf(matchesList.getCode()));
    }
    @PutMapping(value = "/updateMatch/{matchId}/{resultStatus}/{winnerTeamId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Venue.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Matches>> updateMatch(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int matchId,@PathVariable @Valid @Pattern(regexp = "[0-9]*") int resultStatus, @PathVariable @Valid @Pattern(regexp = "[0-9]*") int winnerTeamId) throws Exception {
        Result<String> updateResult = matchesService.updateMatchWinningTeam(matchId,resultStatus,winnerTeamId);
        return new ResponseEntity(updateResult,HttpStatus.valueOf(updateResult.getCode()));
    }
    @PutMapping(value = "/updateMinBet/{matchId}/{minBet}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Venue.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<String>> updateMinimumBet(@PathVariable @Valid @Pattern(regexp = "[0-9]*") int matchId, @PathVariable @Valid @Pattern(regexp = "[0-9]*") int minBet) throws  Exception {
        Result<String> userResult = matchesService.updateMinimumBet(matchId,minBet);
        return new ResponseEntity(userResult,HttpStatus.valueOf(userResult.getCode()));
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 201, message = "success", response = Venue.class),
                    @ApiResponse(code = 400, message = "Bad request", response = ResultException.class),
                    @ApiResponse(code = 500, message = "Unfortunately there is technical error while processing your request", response = ResultException.class)
            }
    )
    public ResponseEntity<Result<Matches>> addMatches(@RequestBody(required = true) @Valid Matches matches) throws  Exception {
        Result<Matches> matchesResult = matchesService.addMatches(matches);
        return new ResponseEntity(matchesResult,HttpStatus.valueOf(matchesResult.getCode()));
    }
}
