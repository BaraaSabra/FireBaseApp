package com.example.firebaseapp;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class CreateBookClass {

    String bookName;
    String autherName;
    String releaseYear;
    String Category;
    int pagesNumer;


    public CreateBookClass(String bookName) {
        this.bookName = bookName;
    }

    public CreateBookClass() {
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public CreateBookClass(String bookName, String autherName, String releaseYear, int pagesNumer, String Category) {
        this.bookName = bookName;
        this.autherName = autherName;
        this.releaseYear = releaseYear;
        this.pagesNumer = pagesNumer;
        this.Category=Category;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPagesNumer() {
        return pagesNumer;
    }

    public void setPagesNumer(int pagesNumer) {
        this.pagesNumer = pagesNumer;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("bookName", bookName);
        result.put("autherName", autherName);
        result.put("releaseYear", releaseYear);
        result.put("Category", Category);
        result.put("pagesNumer", pagesNumer);

        return result;
    }
}
