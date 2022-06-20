package com.atgiugu.test;

import com.atgiugu.dao.BookDao;
import com.atgiugu.dao.impl.BookDaoImpl;
import com.atgiugu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"java从入门到跑路","zoubai",new BigDecimal(999.0),110000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"大家都跑路","zoubai",new BigDecimal(999.0),110000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book querybooks:bookDao.queryBooks())
        {
            System.out.println(querybooks);
        }
    }
    @Test
    public void queryForPageTotalCount(){
        System.out.println(bookDao.queryForpageTotalCount());
    }

    @Test
    public void queryForPageItems(){
        for (Book book : bookDao.queryForPageItem(8, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForpageTotalCountByPrice(){
        System.out.println(bookDao.queryForpageTotalCountByPrice(0,50));
    }

    @Test
    public void queryForPageItemsByPrice(){
        for (Book book : bookDao.queryForPageItemByPrice(0, 4,10,50)) {
            System.out.println(book);
        }
    }
}