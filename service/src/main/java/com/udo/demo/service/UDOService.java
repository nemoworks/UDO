package com.udo.demo.service;

import com.udo.demo.model.UDO;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UDOService {
    private static UDOService UDOService = new UDOService();
    @Autowired
    Nitrite db;

    @Autowired
    ObjectRepository<UDO> docRepository;

    public ObjectRepository<UDO> getDocRepository() {
        return docRepository;
    }

    public static UDOService getUDOService() {
        return UDOService;
    }

    public UDOService() {
        db = Nitrite.builder()
                .openOrCreate();
        docRepository = db.getRepository(UDO.class);
    }

    public void insertDocument(UDO doc) {
        docRepository.insert(doc);
    }

    public void deleteDocument(ObjectFilter filter) {
        docRepository.remove(filter);
    }

    public void updateDocument(ObjectFilter filter, UDO update) {
        docRepository.update(filter, update);
    }
}
