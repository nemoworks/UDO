package info.nemoworks.udo.repository.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.UdoRepository;

public class UDONitriteRepository implements UdoRepository {

    @Autowired
    private static Nitrite db = Nitrite.builder().openOrCreate();

    @Override
    public Udo saveUdo(Udo udo) throws UdoPersistException {
        db.getRepository(Udo.class).insert(udo);
        return null;
    }

    @Override
    public Udo findUdo(String udoi) {
        return db.getRepository(Udo.class).find(ObjectFilters.eq("udoi", udoi)).firstOrDefault();
    }

    @Override
    public UdoSchema findSchemaOfUdo(Udo udo) {
        return db.getRepository(UdoSchema.class).find(ObjectFilters.eq("udoi", udo.getSchema().getUdoi()))
                .firstOrDefault();
    }

    @Override
    public UdoSchema findSchemaById(String udoi) {
        return db.getRepository(UdoSchema.class).find(ObjectFilters.eq("udoi", udoi))
                .firstOrDefault();
    }

}
