package com.udo.demo;

import com.alibaba.fastjson.JSONObject;
import com.udo.demo.model.UDOSchema;
import com.udo.demo.service.UDOSchemaService;
import org.dizitart.no2.Nitrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    UDOSchemaService UDOSchemaService;
    @Override
    public void run(String... args) throws Exception {
        Nitrite db = Nitrite.builder()
                .openOrCreate();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("speed", "Integer");
        jsonObject.put("temperature", "Double");
        UDOSchema airPurifier = new UDOSchema("1", jsonObject);
        UDOSchemaService.insertSchema(airPurifier);
        UDOSchema example = UDOSchemaService.getSchemaRepository()
                .find(eq("id", "1"))
                .firstOrDefault();
        System.out.println(example.toString());
// Create a Nitrite Collection
//        NitriteCollection collection = db.getCollection("test");

//        ObjectRepository<Instance> repository = db.getRepository(Instance.class);
// create a document to populate data
//        Document doc = createDocument("firstName", "John")
//                .put("lastName", "Doe")
//                .put("birthDay", new Date())
//                .put("data", new byte[] {1, 2, 3})
//                .put("fruits", new ArrayList<String>() {{ add("apple"); add("orange"); }})
//                .put("note", "a quick brown fox jump over the lazy dog");

// insert the document
//        collection.insert(doc);
//
//        Instance instance = new Instance();
//        instance.setId(12345);
//        instance.setFirstName("John");
//        instance.setLastName("Doe");
//
//        repository.insert(instance);
//        Instance instance1 = repository.find(eq("firstName", "John"))
//                .firstOrDefault();
//        System.out.println(instance1);

// update the document
        //collection.update(eq("firstName", "John"), createDocument("lastName", "Wick"));

    }
}
