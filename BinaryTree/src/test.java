import com.sun.source.tree.Tree;

import java.util.List;

public class test {
    public static void main(String[] args) throws EmptyTreeExc {
        BST<Integer> bst = new EmptyBST<>();
        bst = bst.insert(5);
        bst = bst.insert(2);
        bst = bst.insert(1);
        bst = bst.insert(3);
        bst = bst.insert(6);



        //print TreePrinter bst
        TreePrinter.print(bst.deleteMin());

        List<Integer> list = List.of(5,2,1,3,6,7,8);
        List<Integer> result = list.subList(0,3);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        BST<Integer> bst2 = BST.mkLeaf(1);


        List<Integer> list2 = List.of(8,4,12,2,6,10,14,1,3,5,7,9,11,13,15);
        BST<Integer> bst3 = BST.fromCollection(list2);
        TreePrinter.print(bst3);
        bst3 = bst3.delete(4);
        TreePrinter.print(bst3);
    }
}
