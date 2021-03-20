package info.nemoworks.udo.repository.nitrite;

import java.util.List;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoSchemaRepository;

@Component
public class NitriteSchemaRepository implements UdoSchemaRepository {

    @Autowired
    private Nitrite db;

    private ObjectRepository<UdoSchema> nitriteRepository;

    public NitriteSchemaRepository(Nitrite db) {
        this.db = db;
        nitriteRepository = db.getRepository(UdoSchema.class);
    }

    public Nitrite getDb() {
        return this.db;
    }

    @Override
    public List<UdoSchema> findAllSchemas() {
        return nitriteRepository.find().toList();
    }

    @Override
    public UdoSchema findSchemaById(String udoi) {
        return nitriteRepository.find(ObjectFilters.eq("udoi", udoi)).firstOrDefault();
    }

    @Override
    public UdoSchema saveSchema(UdoSchema udoSchema) {
        NitriteId saved = nitriteRepository.insert(udoSchema).iterator().next();
        return nitriteRepository.getById(saved);
    }

    @Override
    public void deleteSchemaById(String udoi) {
        nitriteRepository.remove(ObjectFilters.eq("udoi", udoi));
    }

    @Override
    public UdoSchema updateSchema(UdoSchema udoSchema, String udoi) {
        nitriteRepository.update(ObjectFilters.eq("udoi", udoi), udoSchema);
        return nitriteRepository.find(ObjectFilters.eq("udoi", udoi)).firstOrDefault();
    }
}