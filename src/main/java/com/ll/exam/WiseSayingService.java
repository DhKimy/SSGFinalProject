package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {

    private final List<WiseSaying> database;
    private int wiseSayingLastId;

    WiseSayingService(List<WiseSaying> database){
        this.database = database;
        this.wiseSayingLastId = 0;
    }

    public WiseSaying create(String content, String author){
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

    public int findIndex(WiseSaying wiseSaying){
        int index = 0;

        for(int i = 0; i < database.size(); i++){
            if(database.get(i).equals(wiseSaying)){
                index = i;
                break;
            }
        }
        return index;
    }

    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : database) {
            if (wiseSaying.index == paramId) {
                return wiseSaying;
            }
        }
        return null;
    }

    public void remove(int paramIndex) {
        database.remove(paramIndex);
    }

    public void update(int modifyIndex, WiseSaying newWiseSaying) {
        database.set(modifyIndex, newWiseSaying);
    }


    public void save(String path) {
        Util.saveToFile(path, database);
    }
}
