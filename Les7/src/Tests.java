public class Tests {

    @BeforeSuite
    public void test1() {
        System.out.println("test 1 BeforeSuite");
    }
    @BeforeSuite
    public void test9() {
        System.out.println("test 9 BeforeSuite");
    }

    @Test(level = 5)
    public void test2() {
        System.out.println("test 2 prioritet 5");
    }

    @Test(level = 1)
    public void test4() {
        System.out.println("test 4 prioritet 1");
    }

    @Test(level = 10)
    public void test3() {
        System.out.println("test 3 prioritet 10");
    }

    @Test(level = 7)
    public void test7() {
        System.out.println("test 7 prioritet 7");
    }

    @Test(level = 1)
    public void test5() {
        System.out.println("test 5 prioritet 1");
    }

    @AfterSuite
    public void test6() {
        System.out.println("test 6 AfterSuite");
    }
    @Test(level = 9)
    public void test8() {
        System.out.println("test 8 prioritet 9");
    }

}
