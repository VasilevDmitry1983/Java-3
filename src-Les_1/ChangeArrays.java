import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeArrays {
    // Задания 1 и 2.
    public static Object[] changeElements(Object[] arr, int a, int b) {
        Object tempA = arr[a];
        arr[a] = arr[b];
        arr[b] = tempA;
        return arr;
    }

    public static List changeArraysToArrayList(Object[] arr) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
