package kg.kubatbekov.task_2_3.rowMapper;

import kg.kubatbekov.task_2_3.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Course(
                rs.getInt("course_id"),
                rs.getString("course_name"),
                rs.getString("course_description"));
    }
}
