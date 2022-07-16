package com.ll.exam;

import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    App(){
        this.sc = new Scanner(System.in);
    }

    public void run(){
        String path = "data.json";

        System.out.println("== 명언 SSG ==");
        WiseSayingController controller = new WiseSayingController(sc, path);

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);
            controller.rq = rq;

            switch (rq.getPath()) {
                case "등록":
                    controller.create();
                    break;

                case "목록":
                    controller.listUp();
                    break;

                case "삭제":
                    controller.remove();
                    break;

                case "수정":
                    controller.update();
                    break;

                case "빌드":
                    controller.save("저장소");
                    break;

                case "종료":
                    break outer;

            }
        }
    }
}
