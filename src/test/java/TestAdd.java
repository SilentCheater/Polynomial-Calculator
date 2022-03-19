import Model.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestAdd {

    @Test
    public void addTest(){
        Polynomial pol1 = new Polynomial("x^2+2x+1");
        Polynomial pol2 = new Polynomial("x+1");
        Polynomial res = pol1.add(pol2);
        assertEquals("x^2+3.0x+2.0",res.toString());
    }
}
