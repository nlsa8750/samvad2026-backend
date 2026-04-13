package com.samvad.samvad2026.controller;

import com.samvad.samvad2026.model.EventStats;
import com.samvad.samvad2026.model.Journalist;
import com.samvad.samvad2026.repository.EventStatsRepository;
import com.samvad.samvad2026.service.JournalistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/journalists")
public class JournalistController {

    private final JournalistService service;
    private final EventStatsRepository statsRepository;

    public JournalistController(JournalistService service, EventStatsRepository statsRepository) {
        this.service = service;
        this.statsRepository = statsRepository;
    }

    // did not create dto because we are not storing any confidential information, so directly using model 
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Journalist journalist) {
        Journalist saved = service.register(journalist);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Journalist>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> count() {
        EventStats stats = statsRepository.findById("samvad2026_stats").orElse(new EventStats());
        return ResponseEntity.ok(Map.of("total", stats.getRegistrationCount()));
    }
}
