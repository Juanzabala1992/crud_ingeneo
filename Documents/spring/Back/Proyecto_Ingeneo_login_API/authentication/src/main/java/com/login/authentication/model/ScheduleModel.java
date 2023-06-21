package com.login.authentication.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class ScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank(message = "Id no puede estar vacio")
    @NotNull
    private String idUser;

    @NotEmpty
    @NotNull(message = "Id es obligario")
    @Column(nullable = false, unique = true)
    private String idSch;

    @NotEmpty
    @NotNull(message = "Nombre es obligario")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligatorio")
    @Column(nullable = false)
    //@ValidTaskDTO
    private String numero_de_documento;

    @NotEmpty
    @NotNull(message = "Actividades es obligario")
    @Column(nullable = false)
    private String actividades;

    @NotEmpty
    @NotNull(message = "Fecha inicio es obligaria")
    @Column(nullable = false)
    private String fecha_inicio;

    @NotEmpty
    @NotNull(message = "Fecha fin es obligaria")
    @Column(nullable = false)
    private String fecha_fin;

    @NotEmpty
    @NotNull(message = "Horas es obligaria")
    @Column(nullable = false)
    private String horas_actividad;

    @NotEmpty
    @NotNull(message = "Total horas es obligatorio")
    @Column(nullable = false)
    private String total_horas;


    @NotEmpty
    @NotNull(message = "cliente es obligario")
    @Column(nullable = false)
    private String cliente;

    @NotEmpty
    @NotNull(message = "cliente es obligario")
    @Column(nullable = false)
    private String responsable_cliente;

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

    public String getNumero_de_documento() {
        return numero_de_documento;
    }

    public void setNumero_de_documento(String numero_de_documento) {
        this.numero_de_documento = numero_de_documento;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getHoras_actividad() {
        return horas_actividad;
    }

    public void setHoras_actividad(String horas_actividad) {
        this.horas_actividad = horas_actividad;
    }

    public String getTotal_horas() {
        return total_horas;
    }

    public void setTotal_horas(String total_horas) {
        this.total_horas = total_horas;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getResponsable_cliente() {
        return responsable_cliente;
    }

    public String getIdSch() {
        return idSch;
    }

    public void setIdSch(String idSch) {
        this.idSch = idSch;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setResponsable_cliente(String responsable_cliente) {
        this.responsable_cliente = responsable_cliente;
    }
}

