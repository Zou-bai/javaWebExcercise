package com.atgiugu.dao;

import com.atgiugu.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer integer);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForpageTotalCount();

    List<Book> queryForPageItem(int begin, int pageSize);

    Integer queryForpageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemByPrice(int begin, int pageSize, int min, int max);
}
