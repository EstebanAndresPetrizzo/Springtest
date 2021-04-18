package org.andres.test.springboot.app.model;

public class Banco {
    private Long id;
    private String nombre;
    private Integer totalTansferencia;

    public Banco() {
    }

    public Banco(Long id, String nombre, int totalTansferencia) {
        this.id = id;
        this.nombre = nombre;
        this.totalTansferencia = totalTansferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalTansferencia() {
        return totalTansferencia;
    }

    public void setTotalTansferencia(Integer totalTansferencia) {
        this.totalTansferencia = totalTansferencia;
    }

}
