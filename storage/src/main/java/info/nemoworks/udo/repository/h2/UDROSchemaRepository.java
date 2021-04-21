package info.nemoworks.udo.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UDROSchemaRepository extends JpaRepository<UDROSchema, String> {
    UDROSchema findByTableName(String tableName);
    List<UDROSchema> findAll();

    @Transactional
    void deleteByTableName(String tableName);
}
