package viewer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HandTest {

    @Test
    void testContractJointSmallAngle(){
        Hand hand = new Hand(0, 0);
        assertThrows(IllegalArgumentException.class, () -> hand.contractIndex(-100));
        assertThrows(IllegalArgumentException.class, () -> hand.contractMiddle(-100));
    }

    @Test
    void testContractJointLargeAngle(){
        Hand hand = new Hand(0, 0);
        assertThrows(IllegalArgumentException.class, () -> hand.contractIndex(100));
    }

    @Test
    void testLegalAngles(){
        Hand hand = new Hand(0, 0);
        hand.contractRing(90);
        hand.contractRing(-90);
    }
}