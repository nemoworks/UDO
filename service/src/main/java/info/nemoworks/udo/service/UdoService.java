package info.nemoworks.udo.service;

import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.repository.UdoRepository;

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

}