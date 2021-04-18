package org.andres.test.springboot.app.repositories;

import org.andres.test.springboot.app.model.Cuenta;

import java.util.List;

public interface CuentaRepository {
    List<Cuenta> findAll();

    Cuenta findById(Long id);

    void update(Cuenta cuenta);
}
