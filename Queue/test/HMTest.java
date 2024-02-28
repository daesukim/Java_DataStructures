import exceptions.DuplicateKeyException;
import exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HMTest {
    private HM<Integer, String> hm;

    @BeforeEach
    void setUp() {
        hm = new HM<>();
    }

    @Test
    void rehash() throws DuplicateKeyException, NotFoundException {
        int capacity = 2467;
        hm = new HM<>(capacity);
        int size = 2000;

        long start, end;

        // all the keys collide
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i*capacity, Integer.toString(i));
        for (int i=0; i<size; i++)
            hm.get(i*capacity);
        end = System.currentTimeMillis();
        long slow = end - start;

        hm.clear();
        // not a single collision
        start = System.currentTimeMillis();
        for (int i=0; i<size; i++)
            hm.put(i, Integer.toString(i));
        for (int i=0; i<size; i++)
            hm.get(i);
        end = System.currentTimeMillis();
        long fast = end - start;

        assertTrue(slow > fast * 1000); 
    }


    @Test
    @DisplayName("additional test cases")
    void extraTestcases() throws DuplicateKeyException, NotFoundException {
        assertEquals(0, hm.size());
        assertEquals(0, hm.loadFactor());
        assertTrue(hm.isEmpty());
        assertFalse(hm.containsKey(1));
        assertFalse(hm.containsValue("1"));
        assertThrows(NotFoundException.class, () -> hm.get(1));
        hm.put(1, "1");
        assertThrows(DuplicateKeyException.class, () -> hm.put(1, "1"));
        assertEquals(1, hm.size());
        assertFalse(hm.isEmpty());
        assertTrue(hm.containsKey(1));
        assertTrue(hm.containsValue("1"));
        assertEquals("1", hm.get(1));
        hm.put(2, "2");
        assertEquals(2, hm.size());
        hm.clear();
        assertEquals(0, hm.size());

        hm.put(1, "hello");
        hm.put(2, "world");
        hm.put(3, "monster");

        assertEquals(3, hm.size());

        assertEquals("hello", hm.get(1));
        assertEquals("world", hm.get(2));
        assertEquals("monster", hm.get(3));
        assertThrows(NotFoundException.class, () -> hm.get(4));

        double beforeRehash = hm.loadFactor();
        hm.rehash();
        double afterRehash = hm.loadFactor();
        assertTrue(beforeRehash > afterRehash);

        // to see how it is rehashed
        System.out.println(hm.toString());

        assertTrue(hm.containsKey(1));
        assertTrue(hm.containsKey(2));
        assertTrue(hm.containsKey(3));
        assertFalse(hm.containsKey(4));

        assertTrue(hm.containsValue("hello"));
        assertTrue(hm.containsValue("world"));
        assertTrue(hm.containsValue("monster"));
        assertFalse(hm.containsValue("dolly"));
    }

}