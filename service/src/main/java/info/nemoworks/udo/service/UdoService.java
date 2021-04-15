package info.nemoworks.udo.service;

import info.nemoworks.udo.exception.TablePersistException;
import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdoService {

    @Autowired
    private UdoRepository udoRepository;

    public UdoService(UdoRepository udoRepository) {
        this.udoRepository = udoRepository;
    }

    public UdoRepository getUdoRepository() {
        return udoRepository;
    }

    public Udo saveUdo(Udo doc) throws UdoPersistException, TablePersistException {
        System.out.println(doc.toJSON());
//        if (udoRepository.findUdo(doc.getUdoi(), doc.getSchemaId()) != null) {
//            throw new UdoPersistException("A Udo with a same id already exists.");
//        }
        return udoRepository.saveUdo(doc, doc.getSchemaId());
    }

    public Udo findUdoById(String udoi, String collection) throws UdoPersistException, TablePersistException {
//        collection = collection.substring(0, 1).toLowerCase() + collection.substring(1);
        Udo doc = udoRepository.findUdo(udoi, collection);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.\n" + collection + udoi);
        }
        return doc;
    }

    public List<Udo> findAllUdos(String collection) {
        return udoRepository.findAllUdos(collection);
    }

    public List<Udo> deleteUdoById(String udoi, String collection) throws UdoPersistException, TablePersistException {
        Udo doc = udoRepository.findUdo(udoi, collection);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        udoRepository.deleteUdo(udoi, collection);
        return udoRepository.findAllUdos(collection);
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException, TablePersistException {
        Udo doc = udoRepository.findUdo(udoi, udo.getSchemaId());
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return udoRepository.updateUdo(udo, udoi, udo.getSchemaId());
    }
}