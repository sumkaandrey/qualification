package ru.sag.database.db;

public class Courses {
    private String surname;
    private String name;
    private String patronymic;
    private Object nameOrg;
    private Object typeOrg;
    private Object nameDiscipline;
    private Object post;
    private String address;
    private Object gender_male;
    private Object gender_female;

    public Courses(String surname, String name, String patronymic, Object nameOrg, Object typeOrg,
                   Object nameDiscipline, Object post, String address, Object gender_male, Object gender_female) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.nameOrg = nameOrg;
        this.typeOrg = typeOrg;
        this.nameDiscipline = nameDiscipline;
        this.post = post;
        this.address = address;
        this.gender_male = gender_male;
        this.gender_female = gender_female;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Object getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(Object nameOrg) {
        this.nameOrg = nameOrg;
    }

    public Object getTypeOrg() {
        return typeOrg;
    }

    public void setTypeOrg(Object typeOrg) {
        this.typeOrg = typeOrg;
    }

    public Object getNameDiscipline() {
        return nameDiscipline;
    }

    public void setNameDiscipline(Object nameDiscipline) {
        this.nameDiscipline = nameDiscipline;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getGender_male() {
        return gender_male;
    }

    public void setGender_male(Object gender_male) {
        this.gender_male = gender_male;
    }

    public Object getGender_female() {
        return gender_female;
    }

    public void setGender_female(Object gender_female) {
        this.gender_female = gender_female;
    }
}
