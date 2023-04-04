package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Review extends Message{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Float rating;
    private UUID videoID;
    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }
}