package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.model.Post;

import java.util.*;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts;

    public PostRepository(Map<Long, Post> posts) {
        this.posts = posts;
    }

    public List<Post> all() {
        return Collections.unmodifiableList(new ArrayList<>(posts.values()));
    }

    public Optional<Post> getById(long id) {
        Post post = posts.get(id);
        return Optional.ofNullable(post);
    }

    public Post save(Post post) {
        String POST_CAN_NOT_BE_NULL = "Post cannot be null";
        Objects.requireNonNull(post, POST_CAN_NOT_BE_NULL);
        if (post.getId() == 0L) {
            post.setId(posts.size() + 1L);
        } else {
            if (!posts.containsKey(post.getId())) {
                throw new IllegalArgumentException("Cannot update non-existing post.");
            }
        }

        posts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
