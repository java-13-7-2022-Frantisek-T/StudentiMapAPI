package org.example;

public class Student {

    private Long id;            // Atribut - parametr přidán dodatečně a slouží pro identifikaci objektu v mapě
    private String jmeno;
    private String prijmeni;
    private int vek;

    public Student(Long id, String jmeno, String prijmeni, int vek) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

}
