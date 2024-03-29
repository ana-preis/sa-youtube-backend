package com.sa.youtube.dtos;

import com.sa.youtube.models.User;

import java.util.List;
import java.util.UUID;


public record UserOutDTO(

        UUID id,
        String username,
        String email,
        String role,
        List<UUID> subscriptionsIDs,
        List<ReviewOutDTO> reviewList

    ) {

        public UserOutDTO(User user) {
            this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().toString(),
                null, //user.getSubscriptions().stream().map(Category::getId).toList()
                null
            );
        }

        public UserOutDTO(User user, List<UUID> subs, List<ReviewOutDTO> reviews) {
            this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().toString(),
                subs,
                reviews
            );
        }
    }
