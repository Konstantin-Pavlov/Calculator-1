import controller.Calc;
import controller.Calculator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by gorobec on 12.03.16.
 */
public class TestCalculator {
    static Calculator calculator;

    @BeforeClass
    public static void create() {
        calculator = new Calc();
    }
    @Test
    public void  testCalc() {
        double expected = -110.0;
        double actual = calculator.calculate("-Abs(1E+1^2 + 20/ 2)");
        Assert.assertEquals(expected, actual, 0);
    }
}
