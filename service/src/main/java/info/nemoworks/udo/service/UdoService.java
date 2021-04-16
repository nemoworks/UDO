package info.nemoworks.udo.service;

import info.nemoworks.udo.exception.TablePersistException;
import info.nemoworks.udo.monitor.UdoEvent;
import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdoService {

    @Autowired
    private UdoRepository udoRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

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

    public Udo findUdoById(String udoi, String schemaId) throws UdoPersistException, TablePersistException {
//        schemaId = schemaId.substring(0, 1).toLowerCase() + schemaId.substring(1);
        Udo doc = udoRepository.findUdo(udoi, schemaId);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.\n" + schemaId + udoi);
        }
        return doc;
    }

    public List<Udo> findAllUdos(String schemaId) {
        return udoRepository.findAllUdos(schemaId);
    }

    public List<Udo> deleteUdoById(String udoi, String schemaId) throws UdoPersistException, TablePersistException {
        Udo doc = udoRepository.findUdo(udoi, schemaId);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        udoRepository.deleteUdo(udoi, schemaId);
        return udoRepository.findAllUdos(schemaId);
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException, TablePersistException {
        Udo doc = udoRepository.findUdo(udoi, udo.getSchemaId());
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        this.pushListener(udo);
        return udoRepository.updateUdo(udo, udoi, udo.getSchemaId());
    }

    public void pushListener(Udo udo) {
        applicationEventPublisher.publishEvent(new UdoEvent(this, udo));
    }
}