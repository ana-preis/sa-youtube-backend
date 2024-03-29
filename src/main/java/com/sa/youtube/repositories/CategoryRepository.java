package com.sa.youtube.repositories;

import com.sa.youtube.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
    List<Category> findByNameContaining(String text);

    @Query(value = """
        SELECT
            SUM(v.view_count)
        FROM
            video v
        LEFT JOIN
            video_category c
        ON
            v.id = c.video_id
        WHERE
            c.category_id = :id""",
        nativeQuery = true
    )
    Long getViewCount(@Param("id") UUID id);

    @Query(value = """
        SELECT *
        FROM
            category c
        INNER JOIN
            video_category vc
        ON
            c.id = vc.category_id
        WHERE
            c.id = :categoryId AND vc.video_id = :videoId
        """,
        nativeQuery = true
    )
    Optional<Category> checkCategoryFromIds(@Param("videoId") String videoId, @Param("categoryId") UUID categoryId);

    @Query(value = """
    SELECT *
    FROM 
        category c
    INNER JOIN 
        video_category vc
    ON
        c.id = vc.category_id
    WHERE 
        vc.video_id = :videoId
    """,
    nativeQuery = true)
    List<Category> findAllByVideoID(@Param("videoId") String videoId);
}