import Model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDerivative {
    @Test
    public void derivativeTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial res = pol1.derivative();
        assertEquals("2.0x+2.0",res.toString());
    }
}
