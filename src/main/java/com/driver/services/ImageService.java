package com.driver.services;

import com.driver.RequestDTO.ImageUserDTO;
import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image newImage= new Image();
        newImage.setDescription(description);
        newImage.setDimensions(dimensions);

        List<Image> list= blog.getList_of_image();
        list.add(newImage);

        imageRepository2.save(newImage);

        return newImage;
    }

    public void deleteImage(int id){
        imageRepository2.deleteById(id);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(int id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        Image image= imageRepository2.findById(id).get();
        return Integer.parseInt(image.getDimensions());
    }
}
