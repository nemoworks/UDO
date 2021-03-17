package info.nemoworks.udo.service;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;

public class UdoService {

    @Autowired
    private UdoRepository udoRepository; 

    public UdoService(UdoRepository udoRepository) {
        this.udoRepository = udoRepository;
    }

    public void insertDocument(Udo doc) throws UdoPersistException {
        udoRepository.saveUdo(doc);
    }

    public Udo findDocument(String udoi) {
        return udoRepository.findUdo(udoi);
    }

    public UdoSchema findSchemaOfDoc(Udo doc) {
        return udoRepository.findSchemaOfUdo(doc);
    }

}