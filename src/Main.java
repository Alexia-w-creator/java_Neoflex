import java.util.*;

public class Main {
    static String conversionFrom10_Java(int number, int base){
        return Integer.toString(number, base);
    }

    static int conversionTo10_Java(String number, int base){
        return Integer.parseInt(number, base);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = 0;
        int base = 0;
        String convFrom10_result = "";
        int convTo10_result = 0;
        while (true) {
            System.out.print("Input a number: ");
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

            System.out.println("10 to " + base + ": " + convFrom10_result);
            System.out.println(base + " to 10" + ": " + convTo10_result);

            base = 0;
        }
    }
}