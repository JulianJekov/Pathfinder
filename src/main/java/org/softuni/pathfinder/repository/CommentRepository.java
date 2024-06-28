package org.softuni.pathfinder.repository;

import org.softuni.pathfinder.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = """
            select route_id from comments as c
            where approved is true
            group by route_id 
            order by count(route_id) desc
            limit 1""", nativeQuery = true)
    Long getMostCommentedRoutId();
}
