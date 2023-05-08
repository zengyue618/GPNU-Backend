package com.gpnu.repository.query;

import com.gpnu.entity.query.SavedSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedSqlRepository extends JpaRepository<SavedSql, Long> {
    List<SavedSql> findByCreator(String creator);
}
