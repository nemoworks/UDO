package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import org.springframework.stereotype.Repository;

@Repository
public interface UdoRepository {

    public Udo saveUdo(Udo udo) throws UdoPersistException;

    public Udo findUdo(String oid);

    public UdoSchema findSchemaOfUdo(Udo udo);

    public UdoSchema findSchemaById(String udoi);
}