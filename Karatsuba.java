import java.math.BigInteger;
import java.util.Random;

class Karatsuba {
    // Global counter for primitive operations 
    public static long opCount = 0;

    public static BigInteger mult(BigInteger x, BigInteger y) {
        opCount++; // Counter for method call 

        // Checking if input is single digit (Base Case)
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            opCount++;
            return x.multiply(y);
        }

        // Finding maximum length using your logic
        int noOneLength = x.toString().length();
        int noTwoLength = y.toString().length();
        int maxNumLength = Math.max(noOneLength, noTwoLength);
        opCount += 3; // Counters for assignments and comparison

        // Rounding up the divided Max length
        int halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        opCount += 2;

        // Multiplier (10^m)
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength);
        opCount++;

        // Compute the expressions (Using your variable names a, b, c, d)
        BigInteger a = x.divide(maxNumLengthTen);
        BigInteger b = x.remainder(maxNumLengthTen);
        BigInteger c = y.divide(maxNumLengthTen);
        BigInteger d = y.remainder(maxNumLengthTen);
        opCount += 4;

        // Recursive calls (z0, z1, z2)
        BigInteger z0 = mult(a, c);
        BigInteger z2 = mult(b, d);
        BigInteger z1 = mult(a.add(b), c.add(d));
        opCount += 2; // Counters for additions

        // Final result using your Karatsuba logic
        // Formula: z0 * 10^(2*m) + (z1 - z0 - z2) * 10^m + z2
        BigInteger ans = z0.multiply(BigInteger.TEN.pow(halfMaxNumLength * 2))
                .add((z1.subtract(z0).subtract(z2)).multiply(maxNumLengthTen))
                .add(z2);
        opCount += 4; // Counters for subtractions and additions

        return ans;
    }

    public static void main(String[] args) {
        // Values provided by your friend
        int[] nValues = {5, 10, 50, 100, 500, 1000};

        System.out.println("Experimental Results for Karatsuba:");
        System.out.println("n\t\tOperations Count");
        System.out.println("---------------------------------");

        for (int n : nValues) {
            BigInteger num1 = generateRandom(n);
            BigInteger num2 = generateRandom(n);

            opCount = 0; // Reset counter for each n
            mult(num1, num2);
            System.out.println(n + "\t\t" + opCount);
        }
    }

    // Helper to generate n-digit numbers for the experiment
    public static BigInteger generateRandom(int n) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(r.nextInt(10));
        }
        return new BigInteger(sb.toString());
    }
}
