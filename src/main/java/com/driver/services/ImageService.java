package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image newImage= new Image();
        newImage.setDescription(description);
        newImage.setDimensions(dimensions);
        newImage.setBlog(blog);

        List<Image> imageList= new ArrayList<>();
        imageList= blog.getImageList();
        imageList.add(newImage);
        blog.setImageList(imageList);
        blogRepository.save(blog);

        imageRepository2.save(newImage);

        return newImage;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if(image==null)
            return 0;

        String imageDimensions= image.getDimensions();
        double imageA= 0.00;
        double imageB= 0.00;
        int i=0;
        while(i<imageDimensions.length() && imageDimensions.charAt(i)!='X')
        {
            imageA= imageA*10 + (int)(imageDimensions.charAt(i)-'0');
            i++;
        }
        i++;
        while(i<imageDimensions.length())
        {
            imageB= imageB*10 + (int)(imageDimensions.charAt(i)-'0');
            i++;
        }

        double screenA= 0.00;
        double screenB= 0.00;
        i=0;
        while(i<screenDimensions.length() && screenDimensions.charAt(i)!='X')
        {
            screenA= screenA*10 + (int)(screenDimensions.charAt(i)-'0');
            i++;
        }
        i++;
        while(i<screenDimensions.length())
        {
            screenB= screenB*10 + (int)(screenDimensions.charAt(i)-'0');
            i++;
        }

//        double result= Math.floor((screenA*screenB)/(imageA*imageB));     --> this was incorrect!!!
        double result= Math.floor(screenA/imageA)*Math.floor(screenB/imageB);
        return (int)result;
    }
}