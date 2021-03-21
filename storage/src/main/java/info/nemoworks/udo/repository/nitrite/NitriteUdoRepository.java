package info.nemoworks.udo.repository.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;

import java.util.List;

@Component
public class NitriteUdoRepository implements UdoRepository {

    @Autowired
    private Nitrite db;

    private ObjectRepository<Udo> nitriteRepository;

    public NitriteUdoRepository(Nitrite db) {
        this.db = db;
        nitriteRepository = db.getRepository(Udo.class);
    }

    @Override
    public Udo saveUdo(Udo udo) throws UdoPersistException {
        NitriteId saved = nitriteRepository.insert(udo).iterator().next();
        return nitriteRepository.getById(saved);
        //return udo;
    }

    @Override
    public Udo findUdoById(String oid) {
        return db.getRepository(Udo.class).find(ObjectFilters.eq("udoi", oid)).firstOrDefault();
    }

    @Override
    public List<Udo> findAllUdos() {
        return nitriteRepository.find().toList();
    }

    @Override
    public void deleteUdoById(String udoi) {
        nitriteRepository.remove(ObjectFilters.eq("udoi", udoi));
    }

    @Override
    public Udo updateUdo(Udo udo, String udoi) {
        nitriteRepository.update(ObjectFilters.eq("udoi", udoi), udo);
        return nitriteRepository.find(ObjectFilters.eq("udoi", udoi)).firstOrDefault();
    }

}
