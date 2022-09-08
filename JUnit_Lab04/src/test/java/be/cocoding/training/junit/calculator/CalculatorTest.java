package be.cocoding.training.junit.calculator;

import org.junit.*;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;
    int a;
    int b;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }

    @Before
    public void setUp()  {
        System.out.println("setUp");
        calculator = new Calculator();
        a = 10;
        b = 5;
    }

    @After
    public void tearDown(){
        System.out.println("tearDown");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass");
    }
    @Test
    public void testAdd() {
        int actual = calculator.add(a, b);
        assertEquals("actual value is not valid",15, actual);
    }

    @Test
    public void testSubstract() {
        int actual = calculator.substract(a, b);
        assertEquals("actual value is not valid",5, actual);
    }

    @Test
    public void testMultiply() {
        int actual = calculator.multiply(a, b);
        assertEquals(50, actual);
    }

    @Test
    public void testDivide() {
        int actual = calculator.divide(a, b);
        assertEquals(2, actual);
    }
}