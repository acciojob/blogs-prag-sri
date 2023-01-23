package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Blog> blogList= new ArrayList<>();
        for(User user: userRepository1.findAll())
        {
            blogList.addAll(user.getBlogList());
        }
        return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog newBlog= new Blog();

        //updating the blog details
        User user= userRepository1.findById(userId).get();
        newBlog.setUser(user);
        newBlog.setTitle(title);
        newBlog.setContent(content);
        blogRepository1.save(newBlog);

        //Updating the userInformation and changing its blogs
        List<Blog> blogList= user.getBlogList();
        blogList.add(newBlog);
        user.setBlogList(blogList);
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog= findBlogById(blogId);
        Image newImage= imageService1.createAndReturn(blog,description,dimensions);

        List<Image> imageList= blog.getImageList();
        imageList.add(newImage);
        blog.setImageList(imageList);
        blogRepository1.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog= findBlogById(blogId);
        List<Image> imageList= blog.getImageList();
        for(Image image: imageList)
            image.setBlog(null);
//            imageService1.deleteImage(image);
        blogRepository1.delete(blog);
    }
}