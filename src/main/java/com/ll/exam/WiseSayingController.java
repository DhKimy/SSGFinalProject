package com.ll.exam;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    Scanner sc = new Scanner(System.in);
    WiseSayingService wiseSaying_ = new WiseSayingService();
    public Rq rq;

    WiseSayingController(){

    }

    public void create() {
        System.out.println("-- 명언을 등록합니다. --");
        System.out.print("명언) ");
        String content = sc.nextLine();
        System.out.print("작가) ");
        String author = sc.nextLine();

        WiseSaying getId = wiseSaying_.create(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", getId.index);
    }

    public void listUp() {
        System.out.println("-- 현재 목록입니다. --");
        System.out.println("번호 / 명언 / 작가");
        System.out.println("-------------------");

        List<WiseSaying> wiseSaying__ = wiseSaying_.viewAll();

        for(int i = wiseSaying__.size() - 1; i >= 0; i--){
            WiseSaying wiseSaying = wiseSaying__.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying.index, wiseSaying.content, wiseSaying.author);
        }
    }

    public void remove() {
        int paramId = rq.getIntParam("id", 0);

        if(paramId == 0){
            System.out.println("ID를 입력하세요.");
            return;
        }

        WiseSaying findWiseSaying = wiseSaying_.findById(paramId);

        if(findWiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }

        int removeIndex = wiseSaying_.findIndex(wiseSaying_.view(paramId));
        wiseSaying_.remove(removeIndex);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);

    }

    public void update() {
        int updateId = rq.getIntParam("id", 0);

        if(updateId == 0){
            System.out.println("ID를 입력하세요.");
            return;
        }

        WiseSaying foundWiseSaying = wiseSaying_.findById(updateId);

        if(foundWiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", updateId);
            return;
        }
        System.out.println("기존 명언) ");
        System.out.println(wiseSaying_.view(updateId));
        System.out.print("새로운 명언) ");
        String modifyContent = sc.nextLine();
        System.out.print("새로운 작가) ");
        String modifyAuthor = sc.nextLine();

        WiseSaying newWiseSaying = new WiseSaying(updateId, modifyContent, modifyAuthor);

        int updateIndex = wiseSaying_.findIndex(wiseSaying_.view(updateId));
        wiseSaying_.update(updateIndex, newWiseSaying);
        System.out.printf("%d번 명언이 수정되었습니다.\n", updateId);

    }
}

