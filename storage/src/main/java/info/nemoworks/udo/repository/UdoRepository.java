package info.nemoworks.udo.repository;

import java.util.List;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.h2.exception.UdroPersistException;

/*
collection : schemaId
 */
public interface UdoRepository {

    Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException, UdroPersistException;

    Udo findUdo(String udoi, String schemaId) throws UdroPersistException, UdroPersistException;

    List<Udo> findAllUdos(String schemaId);

    void deleteUdo(String udoi, String schemaId) throws UdroPersistException;

    Udo updateUdo(Udo udo, String udoi, String schemaId) throws UdroPersistException;
}