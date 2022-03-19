import Model.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSubtraction {
    @Test
    public void subTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial pol2 = new Polynomial("x+1");
        Polynomial res = pol1.subtract(pol2);
        assertEquals("x^2+x",res.toString());
    }
}
