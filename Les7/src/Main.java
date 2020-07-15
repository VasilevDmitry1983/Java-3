import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {


    static class Node {
        Method method;
        int level;

        public Node(Method method, int level) {
            this.method = method;
            this.level = level;
        }
    }


    static void start(Class<?> testClass) throws IllegalAccessException, InvocationTargetException {
        Tests inst = new Tests();
        ArrayList<Node> methodsBeforeSuite = new ArrayList<>();
        ArrayList<Node> methods = new ArrayList<>();
        ArrayList<Node> methodsAfterSuite = new ArrayList<>();



        for (Method method : testClass.getDeclaredMethods()) {

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                methodsBeforeSuite.add(new Node(method, method.getDeclaredAnnotation(BeforeSuite.class).level()));
            }


            if (method.isAnnotationPresent(Test.class)) {
                methods.add(new Node(method, method.getDeclaredAnnotation(Test.class).level()));
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                methodsAfterSuite.add(new Node(method, method.getDeclaredAnnotation(AfterSuite.class).level()));
            }

        }
        methods.sort((o1, o2) -> o2.level - o1.level);

        if (!methodsBeforeSuite.isEmpty()) {

            methodsBeforeSuite.get(0).method.invoke(inst);
            methodsBeforeSuite.remove(0);
            try {
                if (!methodsBeforeSuite.isEmpty()){
                    throw new RuntimeException();
                }
            }catch (RuntimeException e){
                e.printStackTrace();
            }

        }

        if (!methods.isEmpty()) {
            for (int i = methods.size() - 1; i >= 0 ; i--) {
                methods.get(i).method.invoke(inst);
            }

        }

        if (!methodsAfterSuite.isEmpty()) {
            methodsAfterSuite.get(0).method.invoke(inst);
            methodsAfterSuite.remove(0);
            try {
                if (!methodsAfterSuite.isEmpty()){
                    throw new RuntimeException();
                }
            }catch (RuntimeException e){
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        start(Tests.class);
    }
}
