package forth_class;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.forth_class.Main.createArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class forth_class {
    @Test
    public void createArrayTest() {
        var asd = createArray(3, i->i);
        var asd2 = createArray(3, i->i+2);
        var asd3 = createArray(3, i->i*i);

        assertEquals(Arrays.toString(asd), Arrays.toString(new int[]{0, 1, 2}));
        assertEquals(Arrays.toString(asd2), Arrays.toString(new int[]{2, 3, 4}));
        assertEquals(Arrays.toString(asd3), Arrays.toString(new int[]{0, 1, 4}));
    }

    @Test
    public void testIterate() {


    }
}
