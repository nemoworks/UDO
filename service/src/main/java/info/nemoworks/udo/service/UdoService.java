package info.nemoworks.udo.service;

import info.nemoworks.udo.exception.UdoPersistException;
import info.nemoworks.udo.model.Udo;
import info.nemoworks.udo.model.UdoSchema;
import info.nemoworks.udo.repository.nitrite.UDONitriteRepository;

public class UdoService {

    // private ObjectRepository<UDO> docRepository;

    // public ObjectRepository<UDO> getDocRepository() {
    //     return docRepository;
    // }
    private UDONitriteRepository udoNitriteRepository;

    public UdoService() {
        // docRepository = UDORepository.getRepository();
        udoNitriteRepository = new UDONitriteRepository();
    }

    public void insertDocument(Udo doc) throws UdoPersistException {
        // docRepository.insert(doc);
        udoNitriteRepository.saveUdo(doc);
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