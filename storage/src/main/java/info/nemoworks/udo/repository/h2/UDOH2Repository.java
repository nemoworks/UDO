package info.nemoworks.udo.repository.h2;

import java.util.List;

import info.nemoworks.udo.model.Udo;

public interface UDOH2Repository { // extends JpaRepository<Udo, String> {
    Udo findByUdoi(String udoi);

    List<Udo> findAll();

    void deleteByUdoi(String udoi);
}
