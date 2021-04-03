import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2_ {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        Pattern pattern = Pattern.compile("^(.+)>([0-9]{3})\\|([a-z]{3})\\|([A-Z]{3})\\|([^<>]{3})<\\1$");

        String line = "";
        for (int i = 0; i < n; i++) {
            line = bf.readLine();
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                System.out.printf("Password: %s\n",  matcher.group(2) + matcher.group(3) + matcher.group(4) + matcher.group(5));
            } else {
                System.out.println("Try another password!");
            }
        }
    }
}
