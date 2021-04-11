package info.nemoworks.udo.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/*
计划实现一个以Entity为实例的关系型数据库持久化层
 */
@Component
public interface UTableRepository extends JpaRepository<UTable, String> {
    UTable findByTableName(String tableName);
    List<UTable> findAll();
    void deleteByTableName(String tableName);
}
