package com.login.authentication.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "schedule")
public class ScheduleModel implements Serializable {

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

    @OneToMany(mappedBy = "scheduleModel")
    List<ActividadesModel> actividades;

    @OneToMany(mappedBy = "scheduleModel")
    List<ActividadesExtrasModel> actividades_extras;

    @NotNull(message = "Fecha inicial no puede estar vacía")
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_inicio;

    @NotNull(message = "Fecha final no puede estar vacía")
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_fin;

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

    private String observaciones;
}

