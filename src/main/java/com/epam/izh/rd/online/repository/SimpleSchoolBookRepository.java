package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] names = new SchoolBook[0];
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                names = Arrays.copyOf(names, names.length + 1);
                names[names.length - 1] = book;
            }
        }
        return names;
    }

    @Override
    public boolean removeByName(String name) {
        boolean result = false;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = null;
                if (i == schoolBooks.length - 1) {
                    schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                    result = true;
                } else {
                    System.arraycopy(schoolBooks, i + 1, schoolBooks, i, schoolBooks.length - 1 - i);
                    schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                    result = true;
                    i--;
                }
            }
        }
        return result;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}