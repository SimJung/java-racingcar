package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static final int DEFAULT_RESULT = 0;
    public static final String DEFAULT_DELIMITER = ",|:";
    public static final String CUSTOM_DELIMITER_REGEX_PREFIX = "//(.)\n(.*)";

    public static final Pattern DEFAULT_PATTERN = Pattern.compile(CUSTOM_DELIMITER_REGEX_PREFIX);

    public static int splitAndSum(String text) {
        if (isBlank(text)) {
            return DEFAULT_RESULT;
        }

        String[] split = split(text);
        int[] positives = toPositives(split);
        return sum(positives);
    }

    private static String[] split(String text) {
        Matcher matcher = DEFAULT_PATTERN.matcher(text);

        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            return matcher.group(2)
                    .split(customDelimiter);
        }

        return text.split(DEFAULT_DELIMITER);
    }

    private static int[] toPositives(String[] values) {
        int[] ints = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            ints[i] = toPositive(values[i]);
        }
        return ints;
    }

    private static int toPositive(String value) {
        int num = Integer.parseInt(value);
        if (num < 0) {
            throw new RuntimeException("Negative number!");
        }
        return num;
    }

    private static int sum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    private static boolean isBlank(String text) {
        return text == null || text.isBlank();
    }
}
