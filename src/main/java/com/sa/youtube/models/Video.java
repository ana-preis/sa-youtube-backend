package com.sa.youtube.models;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import com.sa.youtube.dtos.VideoInDTO;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Video {

    @Id
    private String id;
    
    private String title;
    
    private String embedHtml;

    private String thumbnailUrl;
    
    @Column(length = 5000)
    private String description;
    
    private DateTime publishedAt;
    
    private String channelTitle;
    
    private Long likeCount;
    
    private Long viewCount;

    private Long reviewCount;

    private Double averageRating;

    @OneToMany(mappedBy = "video")
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "video_category",
        joinColumns = @JoinColumn(name = "video_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categoryList = new HashSet<>();

    @ManyToMany(mappedBy = "toWatchList")
    private Set<User> userToWatch = new HashSet<>();

    @ManyToMany(mappedBy = "finishedList")
    private Set<User> userFinishedList = new HashSet<>();


    public Video(VideoInDTO dto) {
        this.id = dto.id();
        this.title = dto.title();
        this.embedHtml = dto.embedHtml();
        this.thumbnailUrl = dto.thumbnailUrl();
        this.description = dto.description();
        this.publishedAt = new DateTime(dto.publishedAt());
        this.channelTitle = dto.channelTitle();
        this.likeCount = dto.likeCount();
        this.viewCount = dto.viewCount();
    }

    public Boolean addCategory(Category category) {
        return this.categoryList.add(category);
    }

    public Boolean removeCategory(Category category) {
        return this.categoryList.remove(category);
    }

}