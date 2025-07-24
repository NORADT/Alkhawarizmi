package net.com.alkhawarizmi.models;

public class Ad {

    String courseNo;
    String category;
    String subCategory;
    String price;
    String date;
    int imageId;

    public Ad(String courseNo, String category,
              String subCategory, String price, String date, int imageId) {
        this.courseNo = courseNo;
        this.category = category;
        this.subCategory = subCategory;
        this.price = price;
        this.date = date;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
