package ABC;



public class abc {

    static Object mon = new Object();
    static int num = 1;
    static int count = 0;


    public static void main(String[] args){
        new Thread(() -> {
            while(true){

                try {
                    synchronized (mon) {
                        if (num == 1) {
                            if (count == 5){
                                mon.notify();
                                break;
                            }
                            num = 2;
                            System.out.print("A");
                            mon.notify();
                            mon.wait();
                            Thread.sleep(500);

                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

        new Thread(() -> {
            while(true){

                try {
                    synchronized (mon) {
                        if (count == 5){
                            mon.notify();
                            break;
                        }
                        if (num == 2) {
                            num = 3;
                            System.out.print("B");
                            mon.notify();
                            mon.wait();
                            Thread.sleep(500);
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

        new Thread(() -> {
            while(true){

                try {
                    synchronized (mon) {
                        if (count == 5){
                            mon.notify();
                            break;
                        }
                        if (num == 3) {
                            num = 1;
                            count++;
                            System.out.print("C");
                            mon.notify();
                            mon.wait();
                            Thread.sleep(500);

                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }

}
