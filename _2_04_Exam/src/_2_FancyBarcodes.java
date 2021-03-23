import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2_FancyBarcodes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int linesCount = Integer.parseInt(scanner.nextLine());
        String[] lines = new String[linesCount];

        for (int i = 0; i < linesCount; i++) {
            String line = scanner.nextLine();
            lines[i] = line;
        }
        Pattern patternValidateBarcode = Pattern.compile("@(#)+[A-Z][a-zA-Z0-9]{4,}[A-Z]{1}@(#)+");
        Pattern patternNumber = Pattern.compile("-?\\d+");

        for (String line : lines) {
            Matcher matcherValidateBarcode = patternValidateBarcode.matcher(line);
            if (matcherValidateBarcode.find()) {
                String productGroup = "";
                Matcher matcherNumber = patternNumber.matcher(line);
                if (matcherNumber.find()) {
                    do {
                        String tmpNum = matcherNumber.group();
                        productGroup += tmpNum;
                    } while (matcherNumber.find());
                } else {
                    productGroup = "00";
                }
                System.out.printf("Product group: %s\n", productGroup);
            } else {
                System.out.println("Invalid barcode");
            }
        }
        System.out.println();
    }
}
