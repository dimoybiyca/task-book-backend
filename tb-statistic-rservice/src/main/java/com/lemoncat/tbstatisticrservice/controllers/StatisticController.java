package com.lemoncat.tbstatisticrservice.controllers;

import com.lemoncat.tbstatisticrservice.models.StatisticDays;
import com.lemoncat.tbstatisticrservice.models.StatisticNumbers;
import com.lemoncat.tbstatisticrservice.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
//@CrossOrigin(origins = {"*"}, maxAge = 36000)
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("days")
    public ResponseEntity<StatisticDays> getStatisticDays(@AuthenticationPrincipal Jwt principal) {
        StatisticDays statistic = statisticService.getStatisticDays(principal.getSubject());

        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }

    @GetMapping("numbers")
    public ResponseEntity<StatisticNumbers> getStatisticNumbers(@AuthenticationPrincipal Jwt principal) {
        StatisticNumbers statistic = statisticService.getStatisticNumbers(principal.getSubject());

        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
