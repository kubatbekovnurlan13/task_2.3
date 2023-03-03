package kg.kubatbekov.task_2_3.dao;

import kg.kubatbekov.task_2_3.daoInterface.DAO;
import kg.kubatbekov.task_2_3.model.Course;
import kg.kubatbekov.task_2_3.model.Student;
import kg.kubatbekov.task_2_3.rowMapper.CourseRowMapper;
import kg.kubatbekov.task_2_3.rowMapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO implements DAO<Course> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Course course) {
        String SQL_SAVE = "insert into courses (course_name, course_description) VALUES (?,?);";
        return 0 < jdbcTemplate.update(SQL_SAVE, course.getCourse_name(), course.getCourse_description());
    }

    public boolean addStudent(int course_id, int student_id) {
        String SQL_SAVE = "insert into students_courses (student_id, course_id) VALUES (?,?);";
        return 0 < jdbcTemplate.update(SQL_SAVE, student_id, course_id);
    }

    public List<Student> getAllStudents(String courseName) {
        String SQL_GET_STUDENTS = """
                select students.*\s
                  from courses
                  join students_courses on courses.course_id=students_courses.course_id
                  join students         on students_courses.student_id=students.student_id
                  where courses.course_name=?;""";
        return jdbcTemplate.query(SQL_GET_STUDENTS, new StudentRowMapper(), courseName);
    }

    @Override
    public List<Course> getAll() {
        String SQL_GET_ALL = "select * from courses;";
        return jdbcTemplate.query(SQL_GET_ALL, new CourseRowMapper());
    }

    @Override
    public Course getByName(String name) {
        String SQL_GET = "select * from courses where course_name=?;";
        return jdbcTemplate.query(SQL_GET, new CourseRowMapper(), name).stream().findAny().orElse(new Course());
    }

    @Override
    public boolean update(Course course) {
        String SQL_UPDATE = "update courses set course_name=?, course_description=? where course_id=?;";
        return 0 < jdbcTemplate.update(SQL_UPDATE, course.getCourse_name(), course.getCourse_description(), course.getCourse_id());
    }


    @Override
    public boolean deleteById(int course_id) {
        String SQL_DELETE = "delete from courses where course_id=?;";
        return 0 < jdbcTemplate.update(SQL_DELETE, course_id);
    }

    public boolean deleteStudent(int course_id, int student_id) {
        String SQL_DELETE = "delete from students_courses where course_id=? and student_id=?;";
        return 0 < jdbcTemplate.update(SQL_DELETE, course_id, student_id);
    }
}
