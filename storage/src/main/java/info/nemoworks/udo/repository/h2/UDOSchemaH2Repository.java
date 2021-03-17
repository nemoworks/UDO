package info.nemoworks.udo.repository.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import info.nemoworks.udo.model.UdoSchema;

public interface UDOSchemaH2Repository extends JpaRepository<UdoSchema, String> {
    UdoSchema findByUdoi(String udoi);

    List<UdoSchema> findAll();

    void deleteByUdoi(String Udoi);
}
