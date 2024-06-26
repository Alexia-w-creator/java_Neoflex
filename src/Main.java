import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static String conversionFrom10_Java(int number, int base){
        return Integer.toString(number, base);
    }

    static int conversionTo10_Java(String number, int base){
        return Integer.parseInt(number, base);
    }

    static String conversionFrom10_own(int number, int base){
        String resNumber = "";
        String curNumber = "";
        while (number > 0) {
            curNumber += (number % base);
            if(base == 16){
                switch (curNumber) {
                    case ("10") -> curNumber = "A";
                    case ("11") -> curNumber = "B";
                    case ("12") -> curNumber = "C";
                    case ("13") -> curNumber = "D";
                    case ("14") -> curNumber = "E";
                    case ("15") -> curNumber = "F";
                    default -> {}
                }
            }
            resNumber = curNumber + resNumber;
            number = number / base;
            curNumber = "";
        }
        return resNumber;
    }

    static int conversionTo10_own(String number, int base) {
        int resNumber = 0;
        int curNumber = 0;
        int n = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            if(base == 16){
                switch (number.charAt(i)) {
                    case ('0') -> curNumber = 0;
                    case ('1') -> curNumber = 1;
                    case ('2') -> curNumber = 2;
                    case ('3') -> curNumber = 3;
                    case ('4') -> curNumber = 4;
                    case ('5') -> curNumber = 5;
                    case ('6') -> curNumber = 6;
                    case ('7') -> curNumber = 7;
                    case ('8') -> curNumber = 8;
                    case ('9') -> curNumber = 9;
                    case ('A') -> curNumber = 10;
                    case ('B') -> curNumber = 11;
                    case ('C') -> curNumber = 12;
                    case ('D') -> curNumber = 13;
                    case ('E') -> curNumber = 14;
                    case ('F') -> curNumber = 15;
                    default -> {}
                }
            }
            if(base == 2){
                switch (number.charAt(i)) {
                    case ('0') -> curNumber = 0;
                    case ('1') -> curNumber = 1;
                    default -> {}
                }
            }
            resNumber += curNumber * Math.pow(base, n);
            n++;
        }
        return resNumber;
    }

    static String conversionFrom10_streamAPI(int number, int base){
        if (base == 2){
            return IntStream.rangeClosed(0, 10)
                    .mapToObj(i -> (number & (1 << (10 - i))) >> (10 - i))
                    .map(String::valueOf)
                    .dropWhile(i -> i.equals("0"))
                    .collect(Collectors.joining(""));
        } else if (base == 16) {
            return String.format("%X", number).chars()
                    .mapToObj(c -> Character.toString((char) c))
                    .collect(Collectors.joining(""));
        }
        return "";
    }
    static int conversionTo10_streamAPI(String number, int base){
        if(base == 2){
            String reversedBinary = new StringBuilder(number).reverse().toString();
            return IntStream.range(0, reversedBinary.length())
                    .map(i -> Character.getNumericValue(reversedBinary.charAt(i)) * (1 << i))
                    .sum();
        } else if (base == 16) {
            return number.chars()
                    .mapToObj(c -> Character.isDigit(c) ? c - '0' : Character.toUpperCase(c) - 'A' + 10)
                    .reduce(0, (w, digit) -> w * 16 + digit);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = 0;
        int base = 0;
        String convFrom10_result = "";
        int convTo10_result = 0;
        while (true) {
            System.out.print("\nInput a number: ");
            number = in.nextInt();
            if(number < 0 || number > 1000){
                System.out.println("You can input only a number in the range from 0 to 1000. Try again.");
                continue;
            }

            while (base != 2 && base != 16) {
                System.out.print("Input a base: ");
                base = in.nextInt();
                if (base != 2 && base != 16) {
                    System.out.println("Choose a calculus system: 2 or 16.");
                }
            }
            convFrom10_result = conversionFrom10_Java(number, base);
            convTo10_result = conversionTo10_Java(convFrom10_result, base);

            System.out.println("Built-in methods:");
            System.out.println("10 to " + base + ": " + convFrom10_result);
            System.out.println(base + " to 10" + ": " + convTo10_result);

            convFrom10_result = conversionFrom10_own(number, base);
            convTo10_result = conversionTo10_own(convFrom10_result, base);

            System.out.println("\nOwn methods:");
            System.out.println("10 to " + base + ": " + convFrom10_result);
            System.out.println(base + " to 10" + ": " + convTo10_result);

            convFrom10_result = conversionFrom10_streamAPI(number, base);
            convTo10_result = conversionTo10_streamAPI(convFrom10_result, base);

            System.out.println("\nStream API methods:");
            System.out.println("10 to " + base + ": " + convFrom10_result);
            System.out.println(base + " to 10" + ": " + convTo10_result);

            base = 0;
        }
    }
}