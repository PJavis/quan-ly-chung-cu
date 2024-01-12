package org.example.Model.Function;

import java.util.List;

public interface SelectById<T> {
    List<T> selectById(int id);
}
