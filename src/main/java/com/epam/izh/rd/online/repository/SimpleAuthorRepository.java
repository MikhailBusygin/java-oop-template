package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author result = null;
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                result = author;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            for (int i = 0; i < authors.length; i++) {
                if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                    authors[i] = null;
                    if (i == authors.length - 1) {
                        authors = Arrays.copyOf(authors, authors.length - 1);
                    } else {
                        System.arraycopy(authors, i + 1, authors, i, authors.length - 1 - i);
                        authors = Arrays.copyOf(authors, authors.length - 1);
                    }
                    break;
                }
            }
            return true;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
