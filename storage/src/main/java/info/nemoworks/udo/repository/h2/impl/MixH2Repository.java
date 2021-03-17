package info.nemoworks.udo.repository.h2.impl;

import org.springframework.beans.factory.annotation.Autowired;

import info.nemoworks.udo.repository.h2.UDOH2Repository;
import info.nemoworks.udo.repository.h2.UDOSchemaH2Repository;

public class MixH2Repository {

    @Autowired
    UDOSchemaH2Repository udoSchemaH2Repository;

    @Autowired
    UDOH2Repository udoh2Repository;
}
