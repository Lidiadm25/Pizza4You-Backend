package com.proyecto_final.Pizza4You.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String pass;

    private Double salario;

    @Column(unique = true, nullable = false, length = 20)
    private String dni;
    @Column(length = 50)
    private String ssn;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private Boolean disponible = true;

    @ManyToMany
    @JoinTable(
        name = "empleado_rol",
        joinColumns = @JoinColumn(name = "id_empleado"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;
}