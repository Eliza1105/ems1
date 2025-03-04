package ems.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Class representing an manager (Manager)
@Entity
public class Manager {
    @Id  // Unique employee identifier
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_man")
    private Integer id;
    @Column(name = "manager_name")  //Manager name
    private String name;
    @Column(name = "department_man")  // Department in which the manager works
    private String department;
    @Column(name = "photo_man")  // Link to manager's photo
    private String link;
    @OneToMany(mappedBy = "manager", cascade = {CascadeType.PERSIST, CascadeType.MERGE})  // List of employees under the manager's control
    private List<Employer> employers=new ArrayList<>();

    // Parameterless constructor (required for JPA)
    public Manager() {
    }

    // Constructor with parameters for creating a new manager
    public Manager(String name, String department, String link, List<Employer> employers) {
        this.name = name;
        this.department = department;
        this.link = link;
        this.employers = employers;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



}
