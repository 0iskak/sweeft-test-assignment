public class JavaTasks {
    public boolean isPalindrome(String text) {
        for (int i = 0, j = text.length() - 1; i < j; i++, j--)
            if (text.charAt(i) != text.charAt(j)) return false;

        return true;
    }

    public int minSplit(int amount) {
        var coins = new int[]{50, 20, 10, 5, 1};
        var count = 0;

        for (var i = 0; amount > 0; ) {
            if ((amount -= coins[i]) < 0) {
                amount += coins[i++];
                continue;
            }

            count++;
        }

        return count;
    }

    public int notContains(int[] array) {
        var set = java.util.Arrays.stream(array)
                .filter(i -> i > 0)
                .boxed()
                .collect(java.util.stream.Collectors.toSet());
        for (var i = 1; ; i++)
            if (!set.contains(i)) return i;
    }

    public boolean isProperly(String sequence) {
        var count = 0;
        for (char c : sequence.toCharArray()) {
            if (c == '(') count++;
            else count--;

            if (count < 0) return false;
        }

        return count == 0;
    }

    public int countVariants(int stairsCount) {
        var steps = new int[]{1, 2};

        return countVariants(stairsCount, steps);
    }

    private int countVariants(int stairsCount, int[] steps) {
        if (stairsCount <= 2)
            return Math.max(stairsCount, 0);

        var count = 0;
        for (var step : steps)
            count += countVariants(stairsCount - step, steps);

        return count;
    }


    public static void main(String[] args) {
    }
}
