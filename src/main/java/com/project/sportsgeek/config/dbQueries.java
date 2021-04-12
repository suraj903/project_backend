package com.project.sportsgeek.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "db.queries")
@Component
public class dbQueries {
    private String listOfVenues;
}
