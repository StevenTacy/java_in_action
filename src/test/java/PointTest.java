import com.java_inaction.graphic.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    public void test() {
        var p1 = new Point(1, 2);
        var p2 = p1.moveRightBy(3);
        assertEquals(4, p2.getX());
    }
}
