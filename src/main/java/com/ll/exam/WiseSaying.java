package com.ll.exam;


public class WiseSaying {
    int index;
    String content;
    String author;

    public WiseSaying(int index, String content, String author) {
        this.index = index;
        this.content = content;
        this.author = author;

    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "index=" + index +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
