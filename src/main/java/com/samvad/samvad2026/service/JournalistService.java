package com.samvad.samvad2026.service;

import com.samvad.samvad2026.model.EventStats;
import com.samvad.samvad2026.model.Journalist;
import com.samvad.samvad2026.repository.JournalistRepository;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JournalistService {

    private final JournalistRepository journalistRepository;
    private final MongoTemplate mongoTemplate;

    public JournalistService(JournalistRepository journalistRepository, MongoTemplate mongoTemplate) {
        this.journalistRepository = journalistRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Journalist register(Journalist journalist) {
        if (journalistRepository.existsByMobile(journalist.getMobile())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This mobile number is already registered");
        }
        Journalist saved = journalistRepository.save(journalist);
        incrementRegistrationCount();
        return saved;
    }

    public List<Journalist> getAll() {
        return journalistRepository.findAll();
    }

    private void incrementRegistrationCount() {
        Query query = new Query(Criteria.where("_id").is("samvad2026_stats"));
        Update update = new Update().inc("registrationCount", 1);
        mongoTemplate.findAndModify(
                query,
                update,
                FindAndModifyOptions.options().upsert(true).returnNew(true),
                EventStats.class
        );
    }
}
