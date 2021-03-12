package info.nemoworks.udo.repository.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.exception.UDOPersistException;
import info.nemoworks.udo.model.UDO;
import info.nemoworks.udo.model.UDOSchema;
import info.nemoworks.udo.repository.UDORepository;

public class UDONitriteRepository implements UDORepository {

    @Autowired
    private Nitrite db;

    @Override
    public void saveUDO(UDO udo) throws UDOPersistException {
        db.getRepository(UDO.class).insert(udo);
    }

    @Override
    public UDO findUDO(String udoi) {
        return db.getRepository(UDO.class).find(ObjectFilters.eq("udoi", udoi)).firstOrDefault();
    }

    @Override
    public UDOSchema findSchema(UDO udo) {
        return db.getRepository(UDOSchema.class).find(ObjectFilters.eq("udoi", udo.getSchema().getUdoi()))
                .firstOrDefault();
    }

    @Override
    public void saveSchema(UDOSchema schema) throws UDOPersistException {
        db.getRepository(UDOSchema.class).insert(schema);
    }

}
