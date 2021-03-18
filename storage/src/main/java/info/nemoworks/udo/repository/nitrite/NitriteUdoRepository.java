package info.nemoworks.udo.repository.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;

@Component
public class NitriteUdoRepository implements UdoRepository {

    @Autowired
    private Nitrite db;

    public NitriteUdoRepository(Nitrite db) {
        this.db = db;
    }

    @Override
    public void saveUdo(Udo udo) throws UdoPersistException {
        db.getRepository(Udo.class).insert(udo);
    }

    @Override
    public Udo findUdoById(String oid) {
        return db.getRepository(Udo.class).find(ObjectFilters.eq("udoi", oid)).firstOrDefault();
    }

}
