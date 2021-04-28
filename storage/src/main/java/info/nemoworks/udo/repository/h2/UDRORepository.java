package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.repository.h2.model.UDRO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
计划实现一个以Entity为实例的关系型数据库持久化层
 */
@Component
public interface UDRORepository extends JpaRepository<UDRO, String> {
    UDRO findByTableName(String tableName);

    List<UDRO> findAll();

    @Transactional
    void deleteByTableName(String tableName);
}
