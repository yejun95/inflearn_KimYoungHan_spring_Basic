package hello.core.singleton;

public class StatefulService {

    // 공유 변수에 담아 사용하면 다른 값이 덮어 씌워질 수도 있다.
    // 마치 자바스크립트에서 전역변수로 let을 쓰다가 다른 값이 들어가는 경우와 비슷
//    private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
