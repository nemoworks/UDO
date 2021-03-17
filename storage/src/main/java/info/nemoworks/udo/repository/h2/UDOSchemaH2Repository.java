package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.model.UdoSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UDOSchemaH2Repository extends JpaRepository<UdoSchema, String> {
    UdoSchema findByUdoi(String udoi);
    List<UdoSchema> findAll();
    void deleteByUdoi(String Udoi);
}
