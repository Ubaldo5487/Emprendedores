package com.example.proyectochignautla.Class;

import java.io.Serializable;

public class Reports implements Serializable {
    Integer id;
    String name;
    String fatherlastname;
    String motherlatsname;
    String phonenumber;
    String age;
    String colony;
    String street1;
    String street2;
    String area;
    String problem;
    String description;
    String created_at;
    String status;


    public Reports() {
        this.id = id;
        this.name = name;
        this.fatherlastname = fatherlastname;
        this.motherlatsname = motherlatsname;
        this.phonenumber = phonenumber;
        this.age = age;
        this.street1 = street1;
        this.street2 = street2;
        this.area = area;
        this.problem = problem;
        this.description = description;
        this.created_at = created_at;
        this.status = status;
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

    public String getFatherlastname() {
        return fatherlastname;
    }

    public void setFatherlastname(String fatherlastname) {
        this.fatherlastname = fatherlastname;
    }

    public String getMotherlatsname() {
        return motherlatsname;
    }

    public void setMotherlatsname(String motherlatsname) {
        this.motherlatsname = motherlatsname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}