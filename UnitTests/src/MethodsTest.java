import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MethodsTest {

    @Test
    public void isOperatorTest() {
        Methods a = new Methods();

        assertEquals(false, a.isOperator("123"));
        assertEquals(false, a.isOperator(""));
        assertEquals(false, a.isOperator("1"));
        assertEquals(true, a.isOperator("+"));
    }

    @Test
    public void containsOperator() {
        Methods b = new Methods();

        assertEquals(0, b.containsOperator("+"));
        assertEquals(-1, b.containsOperator("123"));
        assertEquals(-1, b.containsOperator(""));
        assertEquals(4, b.containsOperator("1234+4"));
    }

    @Test
    public void containsPoint() {
        Methods c = new Methods();

        assertEquals(true, c.containsPoint("2.4"));
        assertEquals(false, c.containsPoint("124"));
        assertEquals(true, c.containsPoint("1.2/4.5"));
        assertEquals(false, c.containsPoint("12.4/3"));

    }

}