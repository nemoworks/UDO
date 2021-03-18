package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;

public interface UdoRepository {

    public void saveUdo(Udo udo) throws UdoPersistException;

    public Udo findUdoById(String oid);
}