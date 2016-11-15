package com.lovelystickersua.DTO;

/**
 * Created by devnull on 15.11.16.
 */
public class CategoryDTO {

    private long id;
    private String name;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public CategoryDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
