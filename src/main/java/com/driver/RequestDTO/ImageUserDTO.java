package com.driver.RequestDTO;

public class ImageUserDTO {
    private String description;

    private String dimensions;

    public ImageUserDTO() {
    }

    public ImageUserDTO(String description, String dimensions) {
        this.description = description;
        this.dimensions = dimensions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
