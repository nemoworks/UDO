package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;

import java.util.List;

public interface UdoRepository {

    Udo saveUdo(Udo udo, String collection) throws UdoPersistException;

    Udo findUdoById(String udoi, String collection);

    List<Udo> findAllUdos(String collection);

    void deleteUdoById(String udoi, String collection);

    Udo updateUdo(Udo udo, String udoi, String collection);
}