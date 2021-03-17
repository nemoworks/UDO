package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.model.Udo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UDOH2Repository extends JpaRepository<Udo, String> {
    Udo findByUdoi(String udoi);

    List<Udo> findAll();

    void deleteByUdoi(String udoi);
}
