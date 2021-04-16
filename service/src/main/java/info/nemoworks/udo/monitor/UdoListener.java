package info.nemoworks.udo.monitor;

import info.nemoworks.udo.model.Udo;
import io.micrometer.core.instrument.Counter;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UdoListener implements ApplicationListener<UdoEvent> {

    private final AtomicInteger test_motor_speed;
    private final Counter testCounter;

    private volatile Udo udo;

    public UdoListener(MeterRegistry meterRegistry){
        test_motor_speed = meterRegistry.gauge("custom_motor_speed", new AtomicInteger(0));
        testCounter = meterRegistry.counter("custom_counter");
    }
    @Override
    public void onApplicationEvent(UdoEvent event) {
        System.out.println("事件触发：" + event.getUdo());
        udo = event.getUdo();
        this.schedulingTask();
    }

    //@Scheduled(fixedRateString = "1000", initialDelayString = "0")
    public void schedulingTask() {
        System.out.println("promethueus触发=======");
        int a = udo.getContent().getInteger("Speed");
        a = 5 + (int)(Math.random() * ((100 - 50) + 1));
        test_motor_speed.set(a);
        testCounter.increment();
    }
}
