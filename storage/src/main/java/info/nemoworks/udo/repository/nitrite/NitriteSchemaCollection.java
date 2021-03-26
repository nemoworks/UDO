package info.nemoworks.udo.repository.nitrite;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;
import org.dizitart.no2.Nitrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NitriteSchemaCollectionRepository implements UdoSchemaRepository {

    @Autowired
    private Nitrite db;



    @Override
    public List<UdoSchema> findAllSchemas() {
        return null;
    }

    @Override
    public UdoSchema findSchemaById(String udoi) {
        return null;
    }

    @Override
    public UdoSchema saveSchema(UdoSchema udoSchema) {
        return null;
    }

    @Override
    public void deleteSchemaById(String udoi) {

    }

    @Override
    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) {
        return null;
    }
}
