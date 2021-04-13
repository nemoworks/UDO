package info.nemoworks.udo.repository;

import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
collection : schemaId
 */
public interface UdoRepository {

    Udo saveUdo(Udo udo, String schemaId) throws UdoPersistException, TablePersistException;

    Udo findUdo(String udoi, String schemaId) throws TablePersistException;

    List<Udo> findAllUdos(String schemaId);

    void deleteUdo(String udoi, String schemaId) throws TablePersistException;

    Udo updateUdo(Udo udo, String udoi, String schemaId) throws TablePersistException;
}