package info.nemoworks.udo.repository.h2;

import info.nemoworks.udo.model.Udo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UDOH2Repository extends JpaRepository<Udo, String> {
}
