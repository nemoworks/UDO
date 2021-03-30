package info.nemoworks.udo.service;

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

    public Udo saveUdo(Udo doc) throws UdoPersistException {
        System.out.println(doc.toJSON());
        if (udoRepository.findUdoById(doc.getUdoi(), doc.getCollection()) != null) {
            throw new UdoPersistException("A Udo with a same id already exists.");
        }
        return udoRepository.saveUdo(doc, doc.getCollection());
    }

    public Udo findUdoById(String udoi, String collection) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi, collection);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return doc;
    }

    public List<Udo> findAllUdos(String collection) {
        return udoRepository.findAllUdos(collection);
    }

    public List<Udo> deleteUdoById(String udoi, String collection) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi, collection);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        udoRepository.deleteUdoById(udoi, collection);
        return udoRepository.findAllUdos(collection);
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi, udo.getCollection());
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return udoRepository.updateUdo(udo, udoi, udo.getCollection());
    }
}