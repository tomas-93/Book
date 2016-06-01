package com.tomas.appsopport.model;

/**
 * Created by Tomas on 19/05/2016.
 */
public class Book
{
    private int idBook;
    private String title, author, description, date, editorial, nameFile;
    private byte[] file;

     public int getIdBook() {
          return idBook;
     }

     public void setIdBook(int idBook) {
          this.idBook = idBook;
     }
     public String getNameFile() {
          return nameFile;
     }

     public void setNameFile(String nameFile) {
          this.nameFile = nameFile;
     }

     public byte[] getFile() {
          return file;
     }

     public void setFile(byte[] file) {
          this.file = file;
     }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
