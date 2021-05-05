package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.repository.h2.model.UdroSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UdroSchemaRepository extends JpaRepository<UdroSchema, String> {
    UdroSchema findByTableName(String tableName);

    List<UdroSchema> findAll();

    @Transactional
    void deleteByTableName(String tableName);
}
