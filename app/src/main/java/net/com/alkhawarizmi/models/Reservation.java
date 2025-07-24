package net.com.alkhawarizmi.models;

public class Reservation {

    int id;
    String userId;
    String courseId;

    public Reservation(int id, String userId, String courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
    }

    public Reservation(String userId, String courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
