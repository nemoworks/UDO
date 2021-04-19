package info.nemoworks.udo.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Meter {
    public AtomicInteger value;

    public Meter(AtomicInteger value) {
        this.value = value;
    }

}
