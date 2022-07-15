package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {

    private List<WiseSaying> database;
    private int wiseSayingLastId;

    WiseSayingService(){
        database = new ArrayList<>();
        wiseSayingLastId = 0;

    }

    public WiseSaying write(String content, String author){
        int id = ++wiseSayingLastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        database.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> viewAll(){
        return database;
    }

}
