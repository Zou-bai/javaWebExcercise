package com.atgiugu.test;

import com.atgiugu.pojo.Book;
import com.atgiugu.pojo.Page;
import com.atgiugu.service.BookService;
import com.atgiugu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService =new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"300英雄，登录！","先知",new BigDecimal(1.1),0,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"寄汤来咯！","先知",new BigDecimal(1.1),0,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookService.queryBooks()){
            System.out.println(book);
        }
    }
    @Test
    public void page(){
        Page<Book> page = bookService.page(1, 4);
        System.out.println(page.getItems());
    }
    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, 4,10,50);
        System.out.println(page.getItems());
    }
}