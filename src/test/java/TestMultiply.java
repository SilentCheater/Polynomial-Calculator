import Model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMultiply {
    @Test
    public void multiplyTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial pol2 = new Polynomial("x+1");
        Polynomial res = pol1.multiply(pol2);
        assertEquals("x^3+3.0x^2+3.0x+1.0",res.toString());
    }
}
