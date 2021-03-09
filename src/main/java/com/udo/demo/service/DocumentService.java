package com.udo.demo.service;

import com.udo.demo.model.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class DocumentService {
    private static DocumentService documentService = new DocumentService();
    @Autowired
    Nitrite db;

    @Autowired
    ObjectRepository<Document> docRepository;

    public ObjectRepository<Document> getDocRepository() {
        return docRepository;
    }

    public static DocumentService getDocumentService() {
        return documentService;
    }

    public DocumentService() {
        db = Nitrite.builder()
                .openOrCreate();
        docRepository = db.getRepository(Document.class);
    }

    public void insertDocument(Document doc) {
        docRepository.insert(doc);
    }

    public void deleteDocument(ObjectFilter filter) {
        docRepository.remove(filter);
    }

    public void updateDocument(ObjectFilter filter, Document update) {
        docRepository.update(filter, update);
    }
}
