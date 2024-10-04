package third_class;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.third_class.Battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleshipTest {
    Battleship battleship = new Battleship();
    @BeforeEach
    void setUp() {
        battleship.placeShip(1, 1);
        battleship.placeShip(1, 2);
        battleship.placeShip(1, 3);
    }
    @Nested
    class BattleShipNestedClass {
        @Test
        void testHitCount() {
            battleship.shoot(1, 1);
            assertEquals(1, battleship.hitCount());
        }
        @Test
        void testHitCountMissed() {
            battleship.shoot(2, 1);
            assertEquals(0, battleship.hitCount());
        }
        @Test
        void testSafeCount() {
            assertEquals(3, battleship.safeCount());
        }

        @Test
        void testSafeCountAfterHit() {
            battleship.shoot(1, 1);
            assertEquals(2, battleship.safeCount());
        }
    }

    @Nested
    class SecondNestedClass {
        @Test
        void test() {
            System.out.println("SecondNestedClass.test()");
        }
    }
}
