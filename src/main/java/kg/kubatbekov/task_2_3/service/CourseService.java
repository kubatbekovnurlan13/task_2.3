package kg.kubatbekov.task_2_3.service;

import kg.kubatbekov.task_2_3.dao.CourseDAO;
import kg.kubatbekov.task_2_3.model.Course;
import kg.kubatbekov.task_2_3.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public boolean save(Course course) {
        return courseDAO.save(course);
    }

    public List<Student> findAllStudents(String courseName) {
        return courseDAO.getAllStudents(courseName);
    }

    public boolean addStudent(int course_id, int student_id) {
        return courseDAO.addStudent(course_id, student_id);
    }

    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    public List<Student> getAllStudents(String courseName) {
        return courseDAO.getAllStudents(courseName);
    }


    public Course getByName(String name) {
        return courseDAO.getByName(name);
    }

    public boolean update(Course course) {
        return courseDAO.update(course);
    }

    public boolean deleteById(int course_id) {
        return courseDAO.deleteById(course_id);
    }

    public boolean deleteStudent(int course_id, int student_id) {
        return courseDAO.deleteStudent(course_id, student_id);
    }
}
