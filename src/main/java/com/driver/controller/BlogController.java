package com.driver.controller;

import com.driver.RequestDTO.BlogRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @GetMapping
    public ResponseEntity<Integer> getAllBlogs() {
        int countOfBlogs = 0;
        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBlog(@RequestParam Integer userId ,
                                           @RequestParam String title,
                                           @RequestParam String content) {

        BlogRequestDTO blogRequestDTO= new BlogRequestDTO();
        blogRequestDTO.setTitle(title);
        blogRequestDTO.setContent(content);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {

            return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}




