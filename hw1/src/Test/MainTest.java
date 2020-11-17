package Test;
import com.company.Change;
import com.company.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MainTest {
    @Test
    public void testSumInputWithString() {
        String sumLine = "something";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithEmptyString() {
        String sumLine = "";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithdouble() {
        String sumLine = "2.0";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithNegative() {
        String sumLine = "-1";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputSumWithZero() {
        String sumLine = "0";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkNumberEntering(sumLine);
        });
    }

    @Test
    public void testInputcoinsWithMoresum() {
        String nominalLine = "25 -1 -1 -3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkcoinsEntering(nominalLine,20L);
        });
    }

    @Test
    public void testInputcoinsWithNegative() {
        String nominalLine = "5 -1 -1 -3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkcoinsEntering(nominalLine,20L);
        });
    }

    @Test
    public void testInputcoinsWithZero() {
        String nominalLine = "3 0";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.checkcoinsEntering(nominalLine,20L);
        });
    }



}
