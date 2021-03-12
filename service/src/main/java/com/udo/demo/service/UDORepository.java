package com.udo.demo.service;

import com.udo.demo.model.UDO;
import com.udo.demo.model.UDOSchema;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

public class UDORepository {
    private static Nitrite db = Nitrite.builder()
            .openOrCreate();
    private static ObjectRepository<UDO> udoObjectRepository;

    public static ObjectRepository<UDO> getRepository() {
        udoObjectRepository = db.getRepository(UDO.class);
        return udoObjectRepository;
    }
}
