import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<String> names = createListNames();
        System.out.println(names);
        System.out.println(sortByAlphabet(names));
        System.out.println(sortByLength(names));
        System.out.println(reverseList(names));
    }

    private static List<String> sortByAlphabet(List<String> list) {
        //List<String> result = new ArrayList<>(list);
        //result.sort(null);
        //return result;
        return list.stream().sorted().toList();
    }

    private static List<String> sortByLength(List<String> list) {
        //List<String> result = new ArrayList<>(list);
        //result.sort((o1, o2) -> o1.length() - o2.length());
        //return result;
        return list.stream().sorted((o1, o2) -> o1.length() - o2.length()).toList();
    }

    private static List<String> reverseList(List<String> list) {
        List<String> result = new ArrayList<>(list);
        Collections.reverse(result);
        return result;
        }

    private static List<String> createListNames() {
        List<String> list = new ArrayList<>();
        list.add("Константин");
        list.add("Татьяна");
        list.add("Владимир");
        list.add("Елизавета");
        list.add("Никита");
        list.add("Светлана");
        list.add("Михаил");
        list.add("Ольга");
        list.add("Ирина");
        list.add("Тимофей");
        return list;
    }
}
