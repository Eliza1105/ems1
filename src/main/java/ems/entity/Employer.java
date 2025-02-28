package ems.entity;

import javax.persistence.*;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_emp")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "department")
    private String department;
    @Column(name = "photo")
    private String link;
    @ManyToOne
    @JoinColumn(name = "id_man", nullable = false)
    private Manager manager;

    public Employer() {
    }

    public Employer(String name, String status, String department, String link, Manager managers) {
        this.name = name;
        this.status = status;
        this.department = department;
        this.link = link;
        this.manager = managers;
    }

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
