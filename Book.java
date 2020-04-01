package com.bit.book;

/**
 * @ClassName $ {HJY}
 * @Description TODO
 * @Author $ {USER}
 * @Date $ {DATE} 11:35
 * @Version 1.8
 **/
public class Book {
    public String name;
    public String author;
    public int price;
    public String type; //种类
    public boolean isBorrowed; //是否被借出

    public Book(String name,String author,int price,String type){
        this.name=name;
        this.author=author;
        this.price=price;
        this.type=type;
        //this.isBorrowed=isBorrowed; 可以不初始化
    }

    @Override
    public String toString() {
        return  "Book{" + "name='" + name + '\'' +
                ",author='" + author + '\'' +
                ",price=" + price +
                ",type='" + type + '\'' +
                ((isBorrowed==true)?",已经被借出":"未借出")+
//                ",isBorrowed=" + isBorrowed +
                '}';
    }
}
