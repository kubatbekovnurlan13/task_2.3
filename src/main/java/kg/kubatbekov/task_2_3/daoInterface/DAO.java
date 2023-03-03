package kg.kubatbekov.task_2_3.daoInterface;

import java.util.List;

public interface DAO<T> {
    boolean save(T t);

    T getByName(String name);

    List<T> getAll();

    boolean update(T t);

    boolean deleteById(int id);
}
