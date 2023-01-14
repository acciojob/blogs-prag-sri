package com.driver.controller;
import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.RequestDTO.BlogRequestDTO;
import com.driver.repositories.UserRepository;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping
    public ResponseEntity<Integer> getAllBlogs() {
        int countOfBlogs = blogService.getAllBlogs();
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestParam Integer userId ,
                                           @RequestParam String title,
                                           @RequestParam String content) {

        Blog blog= blogService.createAndReturnBlog(userId,title,content);
        return new ResponseEntity<>(blog,HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
            blogService.addImage(blogId,description,dimensions);
            return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




