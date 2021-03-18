package info.nemoworks.udo.service;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;
import org.springframework.stereotype.Service;

@Service
public class UdoService {

    // private ObjectRepository<UDO> docRepository;

    // public ObjectRepository<UDO> getDocRepository() {
    //     return docRepository;
    // }
    private UDONitriteRepository udoNitriteRepository;

    public UdoService() {
        udoNitriteRepository = new UDONitriteRepository();
    }

    public Udo insertDocument(Udo udo) {
        Udo res = null;
        try {
            res = udoNitriteRepository.saveUdo(udo);
        } catch (UdoPersistException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Udo findDocument(String udoi) {
        return udoNitriteRepository.findUdo(udoi);
    }

    public UdoSchema findSchemaOfDoc(Udo doc) {
        return udoNitriteRepository.findSchemaOfUdo(doc);
    }

    // public void deleteDocument(ObjectFilter filter) {
    //     // docRepository.remove(filter);
    // }

    // public void updateDocument(ObjectFilter filter, UDO update) {
    //     // docRepository.update(filter, update);
    // }
}