import com.company.Change;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeTest {

    @Test
    public void testCount() {
        Long sum=5L;
        ArrayList<Long> coins =  new ArrayList<>();
        coins.add(3L);
        coins.add(2L);
        coins.add(1L);
        //coins.add(4L);
        HashMap<Long, Long> result1 = new HashMap<>();
        Assertions.assertEquals(true, 5==Change.count(sum,coins,result1,0,0));
    }
    @Test
    public void testCount2() {
        Long sum=5L;
        ArrayList<Long> coins =  new ArrayList<>();
        coins.add(3L);
        coins.add(2L);
        coins.add(1L);
        coins.add(4L);
        HashMap<Long, Long> result1 = new HashMap<>();
        Assertions.assertEquals(true, 6==Change.count(sum,coins,result1,0,0));
    }
}