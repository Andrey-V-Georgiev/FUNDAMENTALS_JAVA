import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2_DestinationMapper {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("([=|\\/]){1}([A-Z][a-zA-Z]{2,})\\1");
        Matcher matcher = pattern.matcher(input);
        List<String> validLocations = new ArrayList<>();

        /* Obtain the valid locations */
        while (matcher.find()) {
            String location = matcher.group(2);
            validLocations.add(location);

        }

        /* Calculate travel points */
        int travelPoints = validLocations.stream().mapToInt(String::length).sum();

        /* Print output */
        System.out.printf("Destinations: %s\n", String.join(", ", validLocations));
        System.out.printf("Travel Points: %d\n", travelPoints);
    }
}
