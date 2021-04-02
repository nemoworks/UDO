package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;

import java.util.List;

/*
collection : schemaId
 */
public interface UdoRepository {

    Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException;

    Udo findUdoById(String udoi, String schemaId);

    List<Udo> findAllUdos(String schemaId);

    void deleteUdoById(String udoi, String schemaId);

    Udo updateUdo(Udo udo, String udoi, String schemaId);
}