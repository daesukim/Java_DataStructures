import exceptions.DuplicateKeyException;
import exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws DuplicateKeyException, NotFoundException {
        HM<String, String> hm = new HM<>();
        String str = "hello";
        System.out.println(str.hashCode());

        hm.put("hello", "dolly");
        hm.put("sd", "bally");

        System.out.println(hm.get("hello"));

        System.out.println(hm.size());

        System.out.println(hm.containsKey("sad"));

        System.out.println(hm.containsValue("doll"));
        System.out.println(hm.isEmpty());
        hm.clear();
        System.out.println(hm.isEmpty());

        FastDequeue<PList<Integer>> fd = new FastDequeue<>();
        NEmptyPList<Integer> n = new NEmptyPList<>(1, new EmptyPList<>());

        fd.enqueue(n);

        PList<Integer> p = PList.makeList(1,2,3,4,5,6,7,8,9,10);

        PList<Integer> s = PList.makeList(1,2,3,4,5,6,7,8,9,10);

        PList<Integer> a = new EmptyPList<>();

    

    }
}
