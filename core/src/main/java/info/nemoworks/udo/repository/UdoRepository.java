package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;

public interface UdoRepository {

    public void saveUdo(Udo udo) throws UdoPersistException;

    public Udo findUdo(String oid);

    public UdoSchema findSchemaOfUdo(Udo udo);

    public void saveSchema(UdoSchema schema) throws UdoPersistException;

}
