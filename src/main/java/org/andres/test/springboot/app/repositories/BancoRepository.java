package org.andres.test.springboot.app.repositories;

import org.andres.test.springboot.app.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
