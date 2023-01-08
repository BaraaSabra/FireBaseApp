package com.example.firebaseapp;

import android.widget.ImageView;

public class FavoriteClass {
ImageView image;
String BookName;
int bookYear;

    public FavoriteClass(ImageView image, String BookName, int bookYear) {
        this.image = image;
       this.BookName = BookName;
        this.bookYear = bookYear;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String categoryName) {
        BookName = categoryName;
    }

    public int getbookYear() {
        return bookYear;
    }

    public void setbookYear(int year) {
        this.bookYear = year;
    }
}
