package org.andres.test.springboot.app.repositories;

import org.andres.test.springboot.app.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CuentaRepository extends JpaRepository <Cuenta, Long> {
}
