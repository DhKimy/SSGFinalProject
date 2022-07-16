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
        return  "번호 : " + index +
                " / 명언 : " + content +
                " / 작가 : " + author;
    }

    public String toJson() {
        return """
                {
                    "id": %d,
                    "content": "%s",
                    "author": "%s"
                }
                """
                .stripIndent()
                .formatted(index, content, author)
                .trim();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WiseSaying)) return false;

        WiseSaying other = (WiseSaying) o;

        if (this.index != other.index) return false;
        if (!this.content.equals(other.content)) return false;
        if (!this.author.equals(other.author)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
