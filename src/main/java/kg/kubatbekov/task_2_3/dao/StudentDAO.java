package kg.kubatbekov.task_2_3.dao;

import kg.kubatbekov.task_2_3.daoInterface.DAO;
import kg.kubatbekov.task_2_3.model.Student;
import kg.kubatbekov.task_2_3.rowMapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO implements DAO<Student> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Student student) {
        String SQL_SAVE = "insert into students (first_name, last_name, group_id) VALUES (?,?,?);";
        return 0 < jdbcTemplate.update(SQL_SAVE, student.getFirst_name(), student.getLast_name(), student.getGroup_id());
    }

    @Override
    public List<Student> getAll() {
        String SQL_GET_ALL = "select * from students;";
        return jdbcTemplate.query(SQL_GET_ALL, new StudentRowMapper());
    }

    @Override
    public Student getByName(String name) {
        Student student = new Student();
        String SQL_GET = "select * from students where first_name=?;";
        return jdbcTemplate.query(SQL_GET, new StudentRowMapper(), name)
                .stream().findAny().orElse(student);
    }

    @Override
    public boolean update(Student student) {
        String SQL_UPDATE = "update students set first_name=?, last_name=?, group_id=? where student_id=?;";
        return 0 < jdbcTemplate.update(SQL_UPDATE,
                student.getFirst_name(),
                student.getLast_name(),
                student.getGroup_id(),
                student.getStudent_id());
    }

    @Override
    public boolean deleteById(int student_id) {
        String SQL_DELETE = "delete from students where student_id=?;";
        return 0 < jdbcTemplate.update(SQL_DELETE, student_id);
    }
}
