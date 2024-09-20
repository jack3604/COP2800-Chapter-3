import java.util.HashMap;

public class NumberSystems {
    /**
     * COP 2800 Chapter 3 assignment
     * @param args Takes one binary number as an argument.
     */
    public static void main(String[] args) {
        String input = args[0];

        int decimalNumber = ConvertToDecimal(input, true);
        System.out.println("Decimal: " + decimalNumber + "\n");

        String octalString = ConvertToOctal(input);
        System.out.println("Octal: " + octalString + "\n");

        System.out.println("Hexadecimal: " + ConvertToHex(input));
    }

    /**
     * Converts a binary number to a decimal number.
     * @param binaryNumber Binary number as a string.
     * @param print Pass true to print the output.
     * @return Returns the binary number in decimal form.
     */
    public static int ConvertToDecimal(String binaryNumber, boolean print) {
        int decimalNumber = 0;
        int place = 1;
        for (int i = binaryNumber.length() - 1; i >= 0; i--) {
            decimalNumber += Integer.parseInt(String.valueOf(binaryNumber.charAt(i))) * place;
            place *= 2;
        }

        if (print) {
            PrintByBase(String.valueOf(decimalNumber), 10);
        }

        return decimalNumber;
    }

    /**
     * Converts a binary number to an octal number.
     * @param binaryNumber Binary number as a String.
     * @return Return the binary number in octal form.
     */
    public static String ConvertToOctal(String binaryNumber) {
        int decimalNumber = ConvertToDecimal(binaryNumber, false);
        int remainder = 0;
        String octalString = "";

        while (decimalNumber != 0) {
            remainder = decimalNumber % 8;
            octalString = remainder + octalString;
            decimalNumber = decimalNumber / 8;
        }

        PrintByBase(octalString, 8);

        return octalString;
    }

    /**
     * Converts a binary number to a hexadecimal number.
     * @param binaryNumber Binary number as a String.
     * @return Returns the binary number in hexadecimal form.
     */
    public static String ConvertToHex(String binaryNumber) {
        HashMap<Integer, Character> hexMap = GetIntegerCharacterHashMap();
        int decimalNumber = ConvertToDecimal(binaryNumber, false);
        int remainder = 0;
        String hexNumber = "";

        while (decimalNumber != 0) {
            remainder = decimalNumber % 16;
            hexNumber = hexMap.get(remainder) + hexNumber;
            decimalNumber = decimalNumber / 16;
        }

        PrintByBase(hexNumber, 16, hexMap);

        return hexNumber;
    }

    /**
     * @return Returns a HashMap with the hexadecimal number scheme.
     */
    private static HashMap<Integer, Character> GetIntegerCharacterHashMap() {
        HashMap<Integer, Character> hexMap = new HashMap<>();
        hexMap.put(0, '0');
        hexMap.put(1, '1');
        hexMap.put(2, '2');
        hexMap.put(3, '3');
        hexMap.put(4, '4');
        hexMap.put(5, '5');
        hexMap.put(6, '6');
        hexMap.put(7, '7');
        hexMap.put(8, '8');
        hexMap.put(9, '9');
        hexMap.put(10, 'a');
        hexMap.put(11, 'b');
        hexMap.put(12, 'c');
        hexMap.put(13, 'd');
        hexMap.put(14, 'e');
        hexMap.put(15, 'f');
        return hexMap;
    }

    /**
     * Takes an Integer in the form of a String and prints the desired output for the assignment.
     * @param num Number as a String.
     * @param base Base number as an Integer.
     */
    public static void PrintByBase(String num, int base) {
        int count = 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            System.out.printf("%-5s %s %n", count, num.charAt(i));
            count *= base;
        }
    }

    /**
     * Overload for hexadecimal base 16.
     * @param num Number as a String.
     * @param base Base number as an Integer.
     * @param hexMap HashMap with the hexadecimal values.
     */
    public static void PrintByBase(String num, int base, HashMap<Integer, Character> hexMap) {
        // Swap the keys and values of hexMap
        HashMap<Character, Integer> hexMapSwapped = new HashMap<>();
        for (int i = 0; i < hexMap.size(); i++) {
            hexMapSwapped.put(hexMap.get(i), i);
        }

        int count = 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            System.out.printf("%-5s %-2s %s %n", count, hexMapSwapped.get(num.charAt(i)), num.charAt(i));
            count *= base;
        }
    }
}