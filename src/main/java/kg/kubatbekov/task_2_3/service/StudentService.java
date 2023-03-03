package kg.kubatbekov.task_2_3.service;

import kg.kubatbekov.task_2_3.dao.StudentDAO;
import kg.kubatbekov.task_2_3.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public boolean save(Student student) {
        return studentDAO.save(student);
    }

    public Student getByName(String name) {
        return studentDAO.getByName(name);
    }

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    public boolean deleteById(int student_id) {
        return studentDAO.deleteById(student_id);
    }
}
