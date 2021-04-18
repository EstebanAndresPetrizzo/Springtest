package org.andres.test.springboot.app.repositories;

import org.andres.test.springboot.app.model.Banco;

import java.util.List;

public interface BancoRepository {
    List<Banco> findAll();

    Banco findById(Long id);

    void update (Banco banco);
}
