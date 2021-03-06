package com.linkipedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class TestNode {
    @Test
    public void testEquals() {
        assertTrue(new Node(0, "apple").equals(new Node(0, "apple")));
        assertFalse(new Node(0, "apple").equals(new Node(0, "app")));
        assertFalse(new Node(0, "apple").equals(new Node(1, "apple")));
        assertFalse(new Node(0, "apple").equals(new Node(1, "banana")));
    }

    @Test
    public void testCompareTo() {
        assertTrue(new Node(0, "apple").compareTo(new Node(0, "apple")) == 0);
        assertFalse(new Node(0, "apple").compareTo(new Node(0, "app")) == 0);
        assertTrue(new Node(0, "apple").compareTo(new Node(1, "apple")) == 0);
        assertFalse(new Node(0, "apple").compareTo(new Node(1, "banana")) == 0);

        assertTrue(new Node(-1, "apple").compareTo(new Node(0, "app")) > 0);
        assertTrue(new Node(0, "banana").compareTo(new Node(0, "apple")) > 0);
        assertTrue(new Node(0, "A").compareTo(new Node(10, "a")) < 0);
        assertTrue(new Node(10, "a ").compareTo(new Node(0, "aa")) < 0);
    }

    @Test
    public void testSubstringComparator() {
        Comparator<Node> c = new Node.SubstringComparator();

        // exact match
        assertTrue(c.compare(new Node(-1, "apple"), new Node(0, "apple")) == 0);
        assertTrue(c.compare(new Node(20, ""), new Node(0, "")) == 0);
        assertTrue(c.compare(new Node(100, "z"), new Node(1, "z")) == 0);
        assertTrue(c.compare(new Node(0, "BanaNa"), new Node(1, "BanaNa")) == 0);

        // left substring right
        assertTrue(c.compare(new Node(-1, "app"), new Node(0, "apple")) == 0);
        assertTrue(c.compare(new Node(20, ""), new Node(0, "apple")) == 0);
        assertTrue(c.compare(new Node(100, "Zo"), new Node(1, "Zombie")) == 0);
        assertTrue(c.compare(new Node(0, "B"), new Node(1, "B anaNa")) == 0);

        // right not substring right (flip of above)
        assertTrue(c.compare(new Node(0, "apple"), new Node(-1, "app")) != 0);
        assertTrue(c.compare(new Node(0, "apple"), new Node(20, "")) != 0);
        assertTrue(c.compare(new Node(1, "Zombie"), new Node(100, "Zo")) != 0);
        assertTrue(c.compare(new Node(1, "B anaNa"), new Node(0, "abcd")) != 0);

        // left less than right
        assertTrue(c.compare(new Node(0, "bananab"), new Node(-1, "bananaz")) < 0);
        assertTrue(c.compare(new Node(0, "A"), new Node(-1, "a")) < 0);
        assertTrue(c.compare(new Node(0, "aa"), new Node(-1, "b")) < 0);
        assertTrue(c.compare(new Node(0, "B"), new Node(-1, "aapple")) < 0);

        // left greater than right (flip of above)
        assertTrue(c.compare(new Node(-1, "bananaz"), new Node(0, "bananab")) > 0);
        assertTrue(c.compare(new Node(-1, "a"), new Node(0, "A")) > 0);
        assertTrue(c.compare(new Node(0, "b"), new Node(-1, "aa")) > 0);
        assertTrue(c.compare(new Node(0, "aapple"), new Node(-1, "B")) > 0);
    }
}
