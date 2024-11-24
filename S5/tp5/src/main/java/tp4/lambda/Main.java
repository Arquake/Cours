package tp4.lambda;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        List<String> list = List.of("hello", "world", "hello", "lambda");
        System.out.println(uppercase4(list));
        */
        String adn = "CTGGGACCTAGA";
        Function<Object, String> complementary = o -> {
            if (o == null) {
                throw new Error("Null value");
            }
            return switch (o.toString()) {
                case "A" -> "T";
                case "T" -> "A";
                case "C" -> "G";
                case "G" -> "C";
                default -> throw new Error("No value match");
            };
        };


        IntFunction<Character> codeBase = base -> {
            return switch (base) {
                case 0 -> 'A';
                case 1 -> 'T';
                case 2 -> 'C';
                case 3 -> 'G';
                default -> throw new IllegalArgumentException("Invalid base");
            };
        };

        Supplier<String> s = () -> new Random().ints(1000000, 0, 4)
                .mapToObj(codeBase)
                .map(String::valueOf)
                .collect(Collectors.joining());

        /*
        System.out.println(s.get().chars().filter(c -> c =='A').count());
        System.out.println(s.get().chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting())));
*/
        Function<String, String> convert = b ->
        {
            return new StringBuilder(
                    b.chars().mapToObj(c -> (char) c)
                    .map(x -> complementary.apply(x.toString()))
                    .collect(Collectors.joining())).reverse().toString();
        };




        List<String> sonnet = List.of(
                "From fairest creatures we desire increase,",
                "That thereby beauty's rose might never die,",
                "But as the riper should by time decease,",
                "His tender heir might bear his memory:",
                "But thou contracted to thine own bright eyes,",
                "Feed'st thy light's flame with self-substantial fuel,",
                "Making a famine where abundance lies,",
                "Thy self thy foe, to thy sweet self too cruel:",
                "Thou that art now the world's fresh ornament,",
                "And only herald to the gaudy spring,",
                "Within thine own bud buriest thy content,",
                "And, tender churl, mak'st waste in niggarding:",
                "Pity the world, or else this glutton be,",
                "To eat the world's due, by the grave and thee.");

/*
        System.out.println(sonnet.stream().flatMap(c -> Stream.of(c.split("\\s+"))).collect(Collectors.toList()));
*/
        List<String> alphabet =
                List.of("alfa", "bravo", "charlie", "delta", "echo",
                        "foxtrot", "golf", "hotel", "india", "juliet",
                        "kilo", "lima", "mike", "november", "oscar",
                        "papa", "quebec", "romeo", "sierra", "tango",
                        "uniform", "victor", "whiskey", "x-ray", "yankee",
                        "zulu");


        System.out.println(
                alphabet.stream().sorted(Comparator.comparingInt(String::length)).toList()
        );

        System.out.println(
                alphabet.stream().collect(Collectors.groupingBy(String::length))
        );

        System.out.println(
                alphabet.stream().collect(Collectors.groupingBy(st -> st.charAt(0)))
        );

        System.out.println(
                sonnet.stream().collect(Collectors.groupingBy(st -> st.charAt(0)))
        );

        System.out.println(
                sonnet.stream().collect(Collectors.toMap(
                        v -> v.charAt(0),
                        List::of,
                        (x,y)-> {
                            List<String> result = new ArrayList<>(x);
                            result.addAll(y);
                            return result;
                        }
                ))
        );

        /*System.out.println(
                sonnet.stream().collect(Collectors.toMap(
                        v -> v.charAt(0),
                        v -> new ArrayList<String>(v),
                        (x,y)-> {
                            List<String> result = new ArrayList<>(x);
                            result.addAll(y);
                            return result;
                        }
                ))
        );*/

        /*System.out.println(
                sonnet.stream().flatMap(c -> Stream.of(c.split("\\s+"))).toList().map(v -> Stream.of(v.charAt(0))))
        );*/


        List<String> jours = new ArrayList<>();
        jours.add("lundi");
        jours.add("mardi");
        jours.add("mercredi");
        jours.add("jeudi");
        jours.add("vendredi");
        jours.add("samedi");
        jours.add("dimanche");

        System.out.println(
                jours.stream().filter(st -> st.matches("^.*di$")).toList()
        );

        Path lepath = Paths.get("./src/main/java/tp4/lambda");
        try {

            Stream<String> stream = Files.lines(Paths.get("./src/main/java/tp4/lambda/jours.txt"), StandardCharsets.UTF_8);

            System.out.println(
                    stream.filter(st -> st.matches("^.*di$")).toList()
            );

            System.out.println(
                    stream.filter(st -> st.endsWith("di")).toList()
            );

        }
        catch (Exception e) {
            System.out.println("alp");
        }

    }


    public static List<String> upperCase2(List<String> words) {
        List<String> uppercases = new ArrayList<>();

        words
            .forEach(
                    x ->
                    {
                        uppercases.add(x.toUpperCase());
                    }
            );

        return uppercases;
    }


    public static List<String> uppercase3(List<String> words) {
        List<String> uppercases = new ArrayList<>();
        words.stream().map(String::toUpperCase).
        forEach(uppercases::add);
        return uppercases;
    }

    public static List<String> uppercase4(List<String> words) {
        return words.stream().map(String::toUpperCase).
                collect(Collectors.toList());
    }


    public static List<String> randomString() {
        Random r = new Random();
        List<Integer> randomAdn = new ArrayList<>();
        HashMap<Integer, String> outcomesAdn = new HashMap<>();
        outcomesAdn.put(0,"A");
        outcomesAdn.put(1,"T");
        outcomesAdn.put(2,"C");
        outcomesAdn.put(3,"G");
        for (int i = 0; i < 1_000; i++) {
            randomAdn.add(r.nextInt(4));
        }
        return randomAdn.stream().map(outcomesAdn::get).toList();
    }







}