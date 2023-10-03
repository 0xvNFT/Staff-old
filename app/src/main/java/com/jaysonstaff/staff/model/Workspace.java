package com.jaysonstaff.staff.model;

public class Workspace {
    private String company;
    private String address;
    private String email;
    private String phone;
    private String employees;

    public Workspace(String company, String address, String email, String phone, String employees) {
        this.company = company;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.employees = employees;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }
}

