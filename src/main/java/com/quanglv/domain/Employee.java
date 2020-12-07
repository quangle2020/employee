package com.quanglv.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Data
public class Employee implements Serializable {

    @Id
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

//    @ManyToMany(mappedBy = "roles")
//    private List<Users> users = new ArrayList<>();
}
