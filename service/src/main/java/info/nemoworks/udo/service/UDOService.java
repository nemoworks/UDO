package info.nemoworks.udo.service;

import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;

import info.nemoworks.udo.model.UDO;


public class UDOService {

    // private ObjectRepository<UDO> docRepository;

    // public ObjectRepository<UDO> getDocRepository() {
    //     return docRepository;
    // }


    public UDOService() {
        // docRepository = UDORepository.getRepository();
    }

    public void insertDocument(UDO doc) {
        // docRepository.insert(doc);
    }

    public void deleteDocument(ObjectFilter filter) {
        // docRepository.remove(filter);
    }

    public void updateDocument(ObjectFilter filter, UDO update) {
        // docRepository.update(filter, update);
    }
}
