package com.ll.exam;

import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    WiseSayingService wiseSaying_ = new WiseSayingService();

    public void run(){

        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    System.out.println("-- 명언을 등록합니다. --");
                    System.out.print("명언) ");
                    String content = sc.nextLine();
                    System.out.print("작가) ");
                    String author = sc.nextLine();

                    WiseSaying getId = wiseSaying_.write(content, author);
                    System.out.printf("%d번 명언이 등록되었습니다.\n", getId.index);

                    break;

                case "목록":
                    System.out.println("-- 현재 목록입니다. --");
                    System.out.println("번호 / 명언 / 작가");
                    System.out.println("-------------------");

                    List<WiseSaying> wiseSaying__ = wiseSaying_.viewAll();

                    for(int i = wiseSaying__.size() - 1; i >= 0; i--){
                        WiseSaying wiseSaying = wiseSaying__.get(i);
                        System.out.printf("%d / %s / %s\n", wiseSaying.index, wiseSaying.content, wiseSaying.author);
                    }

                    break;

                case "삭제":
                    int paramId = rq.getIntParam("id", 0);

                    if(paramId == 0){
                        System.out.println("ID를 입력하세요.");
                        break;
                    }

                    WiseSaying findWiseSaying = wiseSaying_.findById(paramId);

                    if(findWiseSaying == null){
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
                        break;
                    }

                    wiseSaying_.remove(paramId);
                    System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);

                    break;

                case "수정":

                    int modifyId = rq.getIntParam("id", 0);

                    if(modifyId == 0){
                        System.out.println("ID를 입력하세요.");
                        break;
                    }

                    WiseSaying foundWiseSaying = wiseSaying_.findById(modifyId);

                    if(foundWiseSaying == null){
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyId);
                        break;
                    }
                    System.out.println("기존 명언) ");
                    System.out.println(wiseSaying_.view(modifyId));
                    System.out.print("새로운 명언) ");
                    String modifyContent = sc.nextLine();
                    System.out.print("새로운 작가) ");
                    String modifyAuthor = sc.nextLine();

                    WiseSaying newWiseSaying = new WiseSaying(modifyId, modifyContent, modifyAuthor);


                    wiseSaying_.modify(modifyId, newWiseSaying);
                    System.out.printf("%d번 명언이 수정되었습니다.\n", modifyId);

                    break;

                case "종료":
                    break outer;

            }
        }
    }
}
