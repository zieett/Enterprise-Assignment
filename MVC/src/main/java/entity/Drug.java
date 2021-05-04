package main.java.entity;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Drugs")
public class Drug implements Comparable {
    @Id
    @Column
    private String id;

    @Column (length = 1024)
    private String name;

    @Column (length = 1024)
    private String preparation;

    @Column (length = 1024)
    private String packaging;

    @Column (length = 1024)
    private String drugGroup;

    @Column (length = 1024)
    private String dosage;

    @Column (length = 1024)
    private String type;

    @Column (length = 1024)
    private String ingredients;

    @Column (length = 1024)
    private String country;
    @Column
    private int money;
    @ManyToOne
    @JoinColumn(name="producer", nullable=false)
    private Producers producers;

    public Drug(){}
    public Drug(String id, String name, String preparation, String packaging, String drugGroup, String dosage, String type, String ingredients, Producers producers, String country){
        this.id = id;
        this.name = name;
        if (preparation.equals("null") || preparation.equals("")){
            this.preparation = "Undefined";
        }
        else {
            this.preparation = preparation;
        }
        if (packaging.equals("null") || packaging.equals("")) {
            this.packaging = "Undefined";
        }
        else {
            this.packaging = packaging;
        }
        if (drugGroup.equals("null") || drugGroup.equals("")) {
            this.drugGroup = "Undefined";
        }
        else {
            this.drugGroup = drugGroup;
        }
        if (dosage.equals("null") || dosage.equals("--") || dosage.equals(" ")) {
            this.dosage = "Undefined";
        }
        else {
            this.dosage = dosage;
        }
        if (type.equals("null") || type.equals("")) {
            this.type = "Undefined";
        }
        else {
            this.type = type;
        }
        if (ingredients.equals("null") || ingredients.equals("")) {
            this.ingredients = "Undefined";
        }
        else {
            this.ingredients = ingredients;
        }
        this.producers = producers;
        if (country.equals("null") || country.equals("")) {
            this.country = "Undefined";
        }
        else {
            this.country = country;
        }
        this.country = country;
        this.money = new Random().nextInt(50)+8;
    }

    @Override
    public String toString() {
        return id +" -- " + name +" -- " +  preparation +" -- " + packaging + " -- " + drugGroup + " -- " + dosage + " -- " + type + " -- "+ ingredients + " -- " + country +" -- "+ producers.getId();
    }

    public String getName() {
        return name;
    }

    public String getDrugGroup() {
        return drugGroup;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int compareTo(Object drug) {
        int compareMoney=((Drug)drug).getMoney();
        return this.money-compareMoney;
    }

    public Producers getProducers() {
        return producers;
    }

    private int getMoney() {
        return money;
    }
}