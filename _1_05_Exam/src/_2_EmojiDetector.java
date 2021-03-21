import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2_EmojiDetector {

    /* 02. Emoji Detector */

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Pattern numberPattern = Pattern.compile("([0-9]{1})");
        Pattern emojiPattern = Pattern.compile("([:,*]{2})([A-Z][a-z]{2,})\\1");

        BigInteger coolThreshold = new BigInteger("1");
        int totalEmojiCount = 0;

        List<String> emojiList = new ArrayList<>();

        /* Obtain the input */
        String input = bufferedReader.readLine();

        /* Calculate the cool threshold */
        Matcher matcherCoolThreshold = numberPattern.matcher(input);
        while (matcherCoolThreshold.find()) {
            int currentNum = Integer.parseInt(matcherCoolThreshold.group(1));
            coolThreshold = coolThreshold.multiply(BigInteger.valueOf(currentNum));
        }

        /* Determine emoji coolness */
        Matcher matcherEmoji = emojiPattern.matcher(input);
        while (matcherEmoji.find()) {
            totalEmojiCount++;

            String group = matcherEmoji.group();
            String group2 = matcherEmoji.group(2);

            int groupCoolness = 0;
            for (char c : group2.toCharArray()) {
                groupCoolness += (int) c;
            }
            int compareResult = coolThreshold.compareTo(BigInteger.valueOf(groupCoolness));
            if (compareResult < 1) {
                emojiList.add(group);
            }
        }

        System.out.printf("Cool threshold: %d\n", coolThreshold);
        System.out.printf("%d emojis found in the text. The cool ones are:\n", totalEmojiCount);

        if (emojiList.size() > 0) {
            emojiList.forEach(System.out::println);
        }
    }
}
