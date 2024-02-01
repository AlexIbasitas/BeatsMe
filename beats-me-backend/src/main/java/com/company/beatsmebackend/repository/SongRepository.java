package com.company.beatsmebackend.repository;

import com.company.beatsmebackend.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {
    // Check if song title exists in collection
    // boolean songExistsByTitle(String title);

}
