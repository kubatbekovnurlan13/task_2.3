package kg.kubatbekov.task_2_3.rowMapper;

import kg.kubatbekov.task_2_3.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("group_id")
        );
    }
}
