package kg.kubatbekov.task_2_3.dao;

import kg.kubatbekov.task_2_3.daoInterface.DAO;
import kg.kubatbekov.task_2_3.model.Group;
import kg.kubatbekov.task_2_3.rowMapper.GroupRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAO implements DAO<Group> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Group group) {
        String SQL_SAVE = "insert into groups (group_name) VALUES (?);";
        return 0 < jdbcTemplate.update(SQL_SAVE, group.getGroup_name());
    }

    @Override
    public Group getByName(String name) {
        Group group = new Group();
        String SQL_GET = "select * from groups where group_name=?;";
        return jdbcTemplate.query(SQL_GET, new GroupRowMapper(), name)
                .stream().findAny().orElse(group);
    }

    @Override
    public List<Group> getAll() {
        String SQL_GET_ALL = "select * from groups;";
        return jdbcTemplate.query(SQL_GET_ALL, new GroupRowMapper());
    }

    public List<Group> findLessOrEqualStudentAccounts(int count) {
        String SQL_GET = """
                  select groups.group_id, groups.group_name
                  from students\s
                  join groups\s
                  on\s
                   students.group_id=groups.group_id\s
                   group by groups.group_id
                   having
                  count(students.group_id) <= ?;\
                """;
        return jdbcTemplate.query(SQL_GET, new GroupRowMapper(), count);
    }

    @Override
    public boolean update(Group group) {
        String SQL_UPDATE = "update groups set group_name=? where group_id=?;";
        return 0 < jdbcTemplate.update(SQL_UPDATE,
                group.getGroup_name(),
                group.getGroup_id());
    }

    @Override
    public boolean deleteById(int group_id) {
        String SQL_DELETE = "delete from groups where group_id=?;";
        return 0 < jdbcTemplate.update(SQL_DELETE, group_id);
    }
}
