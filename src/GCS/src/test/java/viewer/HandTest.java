package viewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandTest {

    Hand hand = new Hand(500, 500);

    @Test
    void testNodeCount() {
        int nodeCount = hand.getChildren().size();
        Assertions.assertEquals(nodeCount, 14);
    }
}