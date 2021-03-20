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

    public void saveUdo(Udo doc) throws UdoPersistException {
        udoRepository.saveUdo(doc);
    }

    public Udo findUdo(String udoi) {
        return udoRepository.findUdoById(udoi);
    }

    public List<Udo> findAllUdos() {
        return udoRepository.findAllUdos();
    }

    public List<Udo> deleteUdoById(String udoi) {
        udoRepository.deleteUdoById(udoi);
        return udoRepository.findAllUdos();
    }

    public Udo updateUdo(Udo udo, String udoi) throws UdoPersistException {
        Udo doc = udoRepository.updateUdo(udo, udoi);
        if (doc == null) {
            throw new UdoPersistException("Doc " + udoi + " does not exist.");
        }
        return doc;
    }
}