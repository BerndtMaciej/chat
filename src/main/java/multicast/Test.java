package multicast;


import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Optional<String> text = Optional.ofNullable(null);
        Integer integer = text.map(t -> t.length()).orElse(0);
        System.out.println(integer);
    }
}
