package com.samvad.samvad2026.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_stats")
public class EventStats {

    @Id
    private String id = "samvad2026_stats"; // single document, fixed id

    private long registrationCount = 0;

    public EventStats() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public long getRegistrationCount() { return registrationCount; }
    public void setRegistrationCount(long registrationCount) { this.registrationCount = registrationCount; }
}
