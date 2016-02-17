/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mcendrowski
 * 
 */
public class Author {
    private Integer authorId;
    private String authorName;
    private Date dateAdded;
    private static int authorCount=0;

    public Author() {
//    authorCount++;
//    this.authorId=authorCount;
    }

    public Author(Integer authorId) {
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Author(Integer authorId, String authorName, Date dateAdded) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }
    
    
    

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.authorId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    
}
