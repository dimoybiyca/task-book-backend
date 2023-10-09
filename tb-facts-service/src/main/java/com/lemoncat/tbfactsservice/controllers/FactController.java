package com.lemoncat.tbfactsservice.controllers;

import com.lemoncat.tbfactsservice.DAO.FactsDAO;
import com.lemoncat.tbfactsservice.models.Fact;
import io.micrometer.tracing.annotation.ContinueSpan;
import io.micrometer.tracing.annotation.NewSpan;
import io.micrometer.tracing.annotation.SpanTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/facts")
@RequiredArgsConstructor
@Slf4j
public class FactController {

    private final FactsDAO factsDAO;

    @GetMapping("daily")
    public ResponseEntity<Map<String, Fact>> getDailyFact() {
        log.info("send daily fact");
        return new ResponseEntity<>(Map.of("fact", factsDAO.getDailyFact()), HttpStatus.OK);
    }
}
