package info.nemoworks.udo.service;

import info.nemoworks.udo.repository.nitrite.NitriteUdoRepository;
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
        if (udoRepository.findUdoById(doc.getUdoi()) != null) {
            throw new UdoPersistException("A Udo with a same id already exists.");
        }
        return udoRepository.saveUdo(doc);
    }

    public Udo findUdoById(String udoi) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return doc;
    }

    public List<Udo> findAllUdos() {
        return udoRepository.findAllUdos();
    }

    public List<Udo> deleteUdoById(String udoi) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        udoRepository.deleteUdoById(udoi);
        return udoRepository.findAllUdos();
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException {
        Udo doc = udoRepository.findUdoById(udoi);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return udoRepository.updateUdo(udo, udoi);
    }
}