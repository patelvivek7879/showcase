package com.showcase.model;

import java.util.*;

public class ShowcaseModel implements java.io.Serializable {
    private List<Category> categories = new LinkedList<Category>();

    public ShowcaseModel() {}

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if (hasCategory(category))
            return;
        this.categories.add(category);
    }

    public boolean hasCategory(Category category) {
        for (Category c : categories) {
            if (c.getTitle().trim().equalsIgnoreCase(category.getTitle().trim()))
                return true;
        }
        return false;
    }

    public Category getCategoryByCode(int code) {
        for (Category c : categories) {
            if (c.getCode() == code)
                return c;
        }
        return null;
    }

    public Category getCategoryByTitle(String title) {
        for (Category c : categories) {
            if (c.getTitle().trim().equalsIgnoreCase(title.trim()))
                return c;
        }
        return null;
    }

    public void updateCategory(Category category) {
        for (Category c : categories) {
            if (c.equals(category)) {
                c.setTitle(category.getTitle());
            }
        }
    }

    public void removeCategory(int code) {
        int i = 0;
        for (Category c : categories) {
            if (c.getCode() == code) {
                categories.remove(i);
            }
            i++;
        }
    }

    public List<Category> getCategories() {
        return this.categories;
    }
}