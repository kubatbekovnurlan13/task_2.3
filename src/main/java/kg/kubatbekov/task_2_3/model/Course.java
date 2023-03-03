package kg.kubatbekov.task_2_3.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Course {
    private int course_id;
    private String course_name;
    private String course_description;

    public Course() {
    }

    public Course(int course_id, String course_name, String course_description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_description = course_description;
    }

    public Course(String course_name, String course_description) {
        this.course_name = course_name;
        this.course_description = course_description;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_description() {
        return course_description;
    }

    @Override
    public String toString() {
        return "Course: " +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", course_description='" + course_description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return course_id == course.course_id && Objects.equals(course_name, course.course_name) && Objects.equals(course_description, course.course_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, course_name, course_description);
    }
}
