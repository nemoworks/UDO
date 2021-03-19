package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;

import java.util.List;

public interface UdoRepository {

    Udo saveUdo(Udo udo) throws UdoPersistException;

    Udo findUdoById(String oid);

    List<Udo> findAllUdos();

    void deleteUdoById(String udoi);

    Udo updateUdo(Udo udo, String udoi);
}