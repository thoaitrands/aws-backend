package com.training.aws_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.aws_backend.model.Post;
import com.training.aws_backend.model.PostRequest;
import com.training.aws_backend.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Posts Management System")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private final PostService postService;

    @Autowired
    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "View a specific post", response = Post.class)
    @GetMapping("{id}")
    public ResponseEntity<Post> post(@PathVariable String id) {
        Optional<Post> post = postService.findById(id);
        return post.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound()
                                                  .build());
    }

    @ApiOperation(value = "View a list of available posts", response = List.class)
    @GetMapping
    public List<Post> list(@RequestParam(required = false) String title) {
        if (StringUtils.isEmpty(title)) {
            return postService.getAll();
        }
        return postService.findByTitle(title);
    }

    @ApiOperation(value = "Save a new post")
    @PostMapping
    public void save(@RequestBody PostRequest request) {
        postService.save(request);
    }

    @PutMapping("{id}/publish")
    public void publishUnpublish(@PathVariable String id, @RequestBody PostRequest request) {
        postService.changePublishedFlag(id, request);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody PostRequest request) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            postService.update(id, request);
        } else {
            postService.save(request);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        postService.delete(id);
    }
}