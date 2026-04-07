package com.samvad.samvad2026.repository;

import com.samvad.samvad2026.model.Journalist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalistRepository extends MongoRepository<Journalist, String> {

    Optional<Journalist> findByMobile(String mobile);

    boolean existsByMobile(String mobile);
}
