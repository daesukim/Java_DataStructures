import com.sun.source.tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalTest {

    @Test
    void InsertTest () throws EmptyTreeExc {
        BST<Integer> bst = new EmptyBST<>(); // empty tree
        // skewed tree
        System.out.println("Skewed tree");
        bst = bst.insert(1);
        bst = bst.insert(2);
        bst = bst.insert(3);
        bst = bst.insert(4);
        bst = bst.insert(5);
        TreePrinter.print(bst);

        // insert 6 and balance the tree
        System.out.println("Insert 6 and balance the tree");
        bst = bst.insertAndBalance(6);
        TreePrinter.print(bst);

    }

    @Test
    void DeleteTest () throws EmptyTreeExc {
        BST<Integer> bst = BST.fromCollection(List.of(5,2,1,3,6));
        System.out.println("Delete min from 5,2,1,3,6");
        TreePrinter.print(bst.deleteMin()); // delete Min
        System.out.println("Delete max from 5,2,1,3,6");
        TreePrinter.print(bst.deleteMax()); // delete Max

        System.out.println("Deleting specific element (3) from 5,2,1,3,6");
        TreePrinter.print(bst.delete(3)); // delete 3

        System.out.println("Deleting specific elements (3 and 6) and balance  from 5,2,1,3,6");
        bst = bst.delete(3);
        bst = bst.deleteAndBalance(6);
        TreePrinter.print(bst);// delete 3 and balance
    }

    @Test
    void FindMinAndMaxTest () throws EmptyTreeExc {
        BST<Integer> bst = BST.fromCollection(List.of(9,5,10,2,8,7,6));
        System.out.println("Show BST for 9,5,10,2,8,7,6");
        TreePrinter.print(bst);
        assertEquals(2, bst.findMin());
        assertEquals(10, bst.findMax());
        assertFalse(bst.isBalanced());
        System.out.println("Show BST for 9,5,10,2,8,7,6,3");
        bst = bst.insert(3);
        TreePrinter.print(bst);
        System.out.println("Removing 5 for 9,5,10,2,8,7,6,3");
        bst = bst.delete(5);
        TreePrinter.print(bst);
        assertFalse(bst.isBalanced());
        System.out.println("Insert 4 and Balance for 9,10,2,8,7,6,3");
        bst = bst.insertAndBalance(4);
        TreePrinter.print(bst);

        // path Test
        assertEquals("01", bst.path(6));
        assertEquals("000", bst.path(2));
        assertEquals("11", bst.path(10));

        BST<Integer> bst2 = BST.fromCollection(List.of(7,4,9,3,6,8,10,2));
        assertTrue(bst2.equals(bst));
    }
}