import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreams {

    private class Pair{
        int key;
        int val;

        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Pair : key = " + this.key + ", val = " + this.val;
        }
    }

    public static void main(String[] args) {

        JavaStreams obj = new JavaStreams();
        List<String> al = Arrays.asList("Jaipur", "Delhi", "Lucknow", "Patna");

//        for(String city : al) {
//            if(!city.startsWith("D")){
//                System.out.println(city);
//            }
//        }

        List<Integer> numbers = Arrays.asList(1, 12,  15, 25, 5, 6, 3, 8);

        List<Pair> pairs = Arrays.asList(obj.new Pair(5, 4), obj.new Pair(2, 5), obj.new Pair(5, 6));

//        numbers.stream().filter(s -> !s)


//        al.stream().filter(s -> !s.startsWith("D")).forEach(y -> {
//            System.out.println("printing...");
//            System.out.println(y);
//        });
//

        // print all the sqaures of all nos which are even in a sorted fashion - 36, 64, 144

//        numbers.stream().filter(x -> x % 2 == 0).map(y -> y * y).sorted().forEach(y -> System.out.println(y));
//        numbers.stream().filter(x -> x % 2 == 1).map(x -> x*x).forEach(y -> System.out.println(y));


//        numbers = numbers.stream().map(y -> y * y).sorted((x,y) -> Integer.compare(y, x)).collect(Collectors.toList());


        // Intermediate operations -> filter, map, sorted
        // Terminal Operations -> forEach, collect
//
//        System.out.println(numbers);


        List<Pair> pairs_list = pairs.stream().sorted((x,y) ->  Integer.compare(y.key, x.key) == 0 ? Integer.compare(x.val, y.val): Integer.compare(y.key, x.key))
                .collect(Collectors.toList());


        System.out.println(pairs_list);
    }

    public static void print(String s){
        System.out.println("printing....");
        System.out.println(s);
    }
}
