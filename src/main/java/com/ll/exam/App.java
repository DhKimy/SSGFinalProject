package com.ll.exam;

import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    WiseSayingService wiseSaying_ = new WiseSayingService();

    public void run(){
        int lastIndex = 0;


        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();


            switch (cmd) {
                case "등록":
                    System.out.println("-- 명언을 등록합니다. --");
                    System.out.print("명언) ");
                    String content = sc.nextLine();
                    System.out.print("작가) ");
                    String author = sc.nextLine();

                    int index = ++lastIndex;

                    wiseSaying_.write(content, author);
                    System.out.printf("%d번 명언이 등록되었습니다.\n", index);

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






                case "종료":
                    break outer;


            }
        }
    }
}
