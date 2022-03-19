import Model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIntegral {
    @Test
    public void integralTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial res = pol1.integrate();
        assertEquals("0.3333333333333333x^3+x^2+x",res.toString());
    }
}
