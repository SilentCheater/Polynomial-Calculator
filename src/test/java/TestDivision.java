import Model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDivision {
    @Test
    public void divTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial pol2 = new Polynomial("x+1");
        Polynomial quotient = pol1.divide(pol2)[0];
        Polynomial reminder = pol1.divide(pol2)[1];
        assertEquals("x+1.0",quotient.toString());
        assertEquals("0.0",reminder.toString());
    }
}
