package com.samvad.samvad2026.repository;

import com.samvad.samvad2026.model.EventStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStatsRepository extends MongoRepository<EventStats, String> {
}
