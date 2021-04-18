package org.andres.test.springboot.app;

import org.andres.test.springboot.app.model.Banco;
import org.andres.test.springboot.app.model.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {
    //public static final Optional<Cuenta> CUENTA_001 = Optional.of(new Cuenta(1L, "Andres", new BigDecimal("1000")));
    //public static final Optional<Cuenta>  CUENTA_002 = Optional.of(new Cuenta(2L, "Jhon", new BigDecimal("2000")));
    //public static final Banco BANCO = new Banco(1L, "Banco Financiero", 0);
    
    public static Optional<Cuenta> crearCuenta001(){
        return Optional.of(new Cuenta(1L, "Andres", new BigDecimal("1000")));
    }    
    
    public static Optional<Cuenta> crearCuenta002(){
        return Optional.of(new Cuenta(1L, "Jhon", new BigDecimal("2000")));
    }    
    
    public static Optional<Banco> crearBanco(){
        return Optional.of(new Banco(1L, "Banco Ciudad", 0));
    }
}
