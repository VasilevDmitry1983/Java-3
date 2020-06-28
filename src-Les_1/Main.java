import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


//      Задание 3.
//      Тестирование задачи.


        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();
        Orange orange4 = new Orange();
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();

        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();

        box1.addFruitToBox(orange1);
        box1.addFruitToBox(orange2);
        box2.addFruitToBox(apple1);
        box2.addFruitToBox(apple2);
        box2.addFruitToBox(apple3);
        box3.addFruitToBox(orange3);
        box3.addFruitToBox(orange4);
        box3.addFruitToBox(apple4);

        System.out.println(box1.getWeight());
        System.out.println(box2.getWeight());
        System.out.println(box1.compare(box2));

        box1.shift(box2);
        box1.shift(box3);

        System.out.println(box3.getWeight());
        System.out.println(box1.getWeight());







    }


}
