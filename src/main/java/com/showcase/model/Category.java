package com.showcase.model;

public class Category implements java.io.Serializable, Comparable<Category> {
    private Integer code;
    private String title;

    public Category() {
        this.code = 0;
        this.title = null;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Category))
            return false;
        return this.code == ((Category) object).code;
    }

    public int compareTo(Category category) {
        return this.code - category.code;
    }

    public int hashCode() {
        return this.code;
    }
}
