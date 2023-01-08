package com.example.firebaseapp;

import java.io.Serializable;

public class CreateLibraryClass implements Serializable {
    private String categoryName;
    private long categoryId;

    @Override
    public String toString() {
        return categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public CreateLibraryClass(String categoryName) {
        this.categoryName = categoryName;
    }
}
