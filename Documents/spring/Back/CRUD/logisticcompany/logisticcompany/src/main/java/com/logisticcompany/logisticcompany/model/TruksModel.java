package com.logisticcompany.logisticcompany.model;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Builder
@Table(name = "truks_logistics")
public class TruksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull(message = "Nombre es obligario")
    @Column(nullable = false)
    private String tipo_producto;

    @NotEmpty
    @Column(nullable = false)
    private String cantidad_producto;

    @NotEmpty
    @NotNull(message = "Segundo apellido es obligario")
    @Column(nullable = false)
    private String fecha_ingreso;

    @NotEmpty
    @NotNull(message = "Direccion es obligaria")
    @Column(nullable = false)
    private String fecha_entrega;

    @NotEmpty
    @NotNull(message = "ciudad es obligaria")
    @Column(nullable = false)
    private String bodega_entega;

    @NotEmpty
    @NotNull(message = "Precion de envio es obligatorio")
    @Column(nullable = false)
    private String precio_envio;


    @NotEmpty
    @NotNull(message = "tipo de documento es obligario")
    //@ValidTaskDTO
    @Column(nullable = false)
    private String placa_vehiculo;

    @NotEmpty
    @NotNull(message = "Numero de documento es obligario")
    @Column(nullable = false, unique = true)
    private String guide;

    public TruksModel() {

    }

    public TruksModel(Long id, @NotBlank String tipo_producto, String cantidad_producto, @NotBlank String fecha_ingreso,
                      @NotBlank String fecha_entrega, String bodega_entega, String precio_envio, String placa_vehiculo, String numero_guia) {
        super();
        this.id = id;
        this.tipo_producto = tipo_producto;
        this.cantidad_producto = cantidad_producto;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_entrega = fecha_entrega;
        this.bodega_entega = bodega_entega;
        this.precio_envio = precio_envio;
        this.placa_vehiculo = placa_vehiculo;
        this.guide = numero_guia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(String cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getBodega_entega() {
        return bodega_entega;
    }

    public void setBodega_entega(String bodega_entega) {
        this.bodega_entega = bodega_entega;
    }

    public String getPrecio_envio() {
        return precio_envio;
    }

    public void setPrecio_envio(String precio_envio) {
        this.precio_envio = precio_envio;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
