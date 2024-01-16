import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        System.out.println(date.compareTo(date2));
    }


}