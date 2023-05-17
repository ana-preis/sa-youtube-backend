package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;

import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        String description,
        Integer userCount,
        Integer videoCount,
        Long viewCount,
        List<VideoOutDTO> videoDTOList) {

    public CategoryDTO(Category category, List<VideoOutDTO> videoList) {
        this (
                category.getId(),
                category.getName(),
                category.getDescription(),
                null,//category.getUserList().size(),
                videoList.size(),
                category.getViewCount(),
                videoList
        );
    }

    public CategoryDTO(Category category) {
        this (
                category.getId(),
                category.getName(),
                category.getDescription(),
                null,
                null,
                null,
                null
        );
    }

}
