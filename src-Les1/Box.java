import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> boxList = new ArrayList<>();

    public Box() {

    }

    public float getWeight(){
        if (boxList.size() == 0){
            return 0;
        }else{
             float weight = boxList.get(0).getWeightFruit() * boxList.size();
             return  weight;
        }

    }

    public void addFruitToBox(T fruit) {
        if (boxList.size() == 0){
            boxList.add(fruit);
        }else{
            if (boxList.get(0).getClass().equals(fruit.getClass())){
                boxList.add(fruit);
            }else{
                System.out.println("Ошибка добавления");
            }
        }

    }

    public boolean compare(Box boxToCompare){
        if (boxToCompare.getWeight() == getWeight() ){
            return true;
        }else {
            return false;
        }
    }

    public void shift(Box boxToShift){
        if (boxList.size() == 0 || boxToShift.boxList.size() == 0){
            boxToShift.boxList.addAll(boxList);
            boxList.clear();
        }else{
            if (boxList.get(0).getClass().equals(boxToShift.boxList.get(0).getClass())){
                boxToShift.boxList.addAll(boxList);
                boxList.clear();
            }else{
                System.out.println("Ошибка пересыпки");
            }
        }



    }
}
