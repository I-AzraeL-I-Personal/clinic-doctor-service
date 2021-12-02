package com.mycompany.doctorservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "phoneNumber cannot be null")
    @Size(max = 9, message = "phoneNumber cannot be longer than {max}")
    private String phoneNumber;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private Voivodeship voivodeship;

    @Column(nullable = false)
    @NotBlank(message = "city cannot be blank")
    @Size(max = 100, message = "city cannot be longer than {max}")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "houseNum cannot be blank")
    @Size(max = 100, message = "street cannot be longer than {max}")
    private String street;

    @Column(nullable = false)
    @NotBlank(message = "houseNum cannot be blank")
    @Size(max = 10, message = "houseNum cannot be longer than {max}")
    private String houseNum;

    @Size(max = 10, message = "flatNum cannot be longer than {max}")
    private String flatNum;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
