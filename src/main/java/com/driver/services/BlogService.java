package com.driver.services;

import com.driver.RequestDTO.BlogRequestDTO;
import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> list= new ArrayList<>();
        list.add((Blog) blogRepository1.findAll());
        return list;
    }

    public Integer getAllBlogs() {
        int countOfBlogs = 0;
        for(User user: userRepository1.findAll())
        {
            countOfBlogs+= user.getList_of_blogs().size();
        }
        return countOfBlogs;
    }

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        //updating the blog details
        //Updating the userInformation and changing its blogs

        Blog newBlog= new Blog();
        newBlog.setTitle(title);
        newBlog.setContent(content);
        blogRepository1.save(newBlog);

        User user= userRepository1.findById(userId).get();
        List<Blog> list= user.getList_of_blogs();
        list.add(newBlog);

        return newBlog;
    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Image newImage= new Image();
        newImage.setDescription(description);
        newImage.setDimensions(dimensions);

        Blog blog= blogRepository1.findById(blogId).get();
        List<Image> list= blog.getList_of_image();
        list.add(newImage);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
