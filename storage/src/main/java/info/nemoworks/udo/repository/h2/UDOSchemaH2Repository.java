package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.model.UdoSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UDOSchemaH2Repository extends JpaRepository<UdoSchema, String> {
}
