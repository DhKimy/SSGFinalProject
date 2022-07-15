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

    public WiseSaying view(int modifyId) {
        int index = 0;
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).index == modifyId){
                index = i;
            }
        }
        return database.get(index);

    }

    public List<WiseSaying> viewAll(){
        return database;
    }

    public WiseSaying findById(int paramId) {
        for(int i = 0; i < database.size(); i++){
            if(database.get(i).index == paramId){
                return database.get(i);
            }
        }
        return null;
    }

    public void remove(int paramId) {
        database.remove(paramId - 1);
    }

    public void modify(int modifyId, WiseSaying newWiseSaying) {

        database.set(modifyId - 1, newWiseSaying);
    }


}
