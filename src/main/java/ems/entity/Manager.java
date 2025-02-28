package ems.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_man")
    private Integer id;
    @Column(name = "manager_name")
    private String name;
    @Column(name = "department_man")
    private String department;
    @Column(name = "photo_man")
    private String link;
    @OneToMany(mappedBy = "manager", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employer> employers=new ArrayList<>();

    public Manager() {
    }

    public Manager(String name, String department, String link, List<Employer> employers) {
        this.name = name;
        this.department = department;
        this.link = link;
        this.employers = employers;
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
