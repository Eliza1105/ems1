package ems.entity;

import javax.persistence.*;

//Class representing an employee (Employer)
@Entity
public class Employer {
    @Id // Unique employee identifier
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_emp")
    private Integer id;
    @Column(name = "name")//Employee name
    private String name;
    @Column(name = "status") // Employee status (active, passive)
    private String status;
    @Column(name = "department")  // Department in which the employee works
    private String department;
    @Column(name = "photo") // Link to employee's photo
    private String link;
    @ManyToOne
    @JoinColumn(name = "id_man", nullable = false)  // Communication with the manager to whom the employee belongs
    private Manager manager;

    // Parameterless constructor (required for JPA)
    public Employer() {
    }

    // Constructor with parameters for creating a new employee
    public Employer(String name, String status, String department, String link, Manager managers) {
        this.name = name;
        this.status = status;
        this.department = department;
        this.link = link;
        this.manager = managers;
    }
//Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
