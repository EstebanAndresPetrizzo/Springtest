package org.andres.test.springboot.app;

import org.andres.test.springboot.app.model.Banco;
import org.andres.test.springboot.app.model.Cuenta;

import java.math.BigDecimal;

public class Datos {
    public static final Cuenta CUENTA_001 = new Cuenta(1L, "Andres", new BigDecimal("1000"));
    public static final Cuenta CUENTA_002 = new Cuenta(2L, "Jhon", new BigDecimal("2000"));
    public static final Banco BANCO = new Banco(1L, "Banco Financiero", 0);
}
