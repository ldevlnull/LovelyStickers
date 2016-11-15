package com.lovelystickersua.POJO;

import javax.persistence.*;
import java.util.List;

/**
 * Created by devnull on 15.11.16.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Category(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category() {
    }
}
