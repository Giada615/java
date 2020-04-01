package com.bit.operation;

import com.bit.book.BookList;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 11:38
 * @Version 1.8
 **/
public class DisplayOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("DisplayOperation");

        // 打印所有书籍
        for (int i = 0; i <bookList.getSize() ; i++) {
            System.out.println(bookList.getBook(i));
        }
    }
}
