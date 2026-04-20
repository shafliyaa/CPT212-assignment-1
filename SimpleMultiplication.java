import java.util.*;

public class SimpleMultiplication {
    long primitiveOps = 0;

    public int[] multiply(int[] arr1, int[] arr2) {
        int n = arr1.length; primitiveOps++;
        int[] finalResult = new int[2 * n]; primitiveOps++;

        for (int i = n - 1; i >= 0; i--) {
            primitiveOps += 2;
            int carry = 0; primitiveOps++;
            StringBuilder partialRow = new StringBuilder();
            StringBuilder carryRow = new StringBuilder();

            for (int j = n - 1; j >= 0; j--) {
                primitiveOps += 2;
                int multi = arr1[j] * arr2[i] + carry + finalResult[i + j + 1];
                primitiveOps += 4;
                int partialProduct = multi % 10; primitiveOps++;
                carry = multi / 10; primitiveOps++;
                finalResult[i + j + 1] = partialProduct; primitiveOps++;
                partialRow.insert(0, partialProduct);
                carryRow.insert(0, carry);
            }
            finalResult[i] += carry; primitiveOps++;

            if (n <= 10) {
                System.out.println("Partial products for x" + arr2[i] + ": " + partialRow);
                System.out.println("Carriers for x" + arr2[i] + ":        " + carryRow);
            }
        }
        return finalResult;
    }

    public void printFinalResult(int[] result) {
        boolean leadingZero = true;
        System.out.print("Final Result: ");
        for (int digit : result) {
            if (leadingZero && digit == 0) continue;
            leadingZero = false;
            System.out.print(digit);
        }
        if (leadingZero) System.out.print(0);
        System.out.println();
    }

    public int[] generateRandomNumber(int n, Random rand) {
        int[] num = new int[n];
        num[0] = rand.nextInt(9) + 1;
        for (int i = 1; i < n; i++) num[i] = rand.nextInt(10);
        return num;
    }

    public static void main(String[] args) {
        SimpleMultiplication sm = new SimpleMultiplication();
        Random rand = new Random();
        int[] testSizes = {5, 10, 50, 100, 500, 1000};

        System.out.println("n\tPrimitive Operations");
        for (int n : testSizes) {
            sm.primitiveOps = 0;
            int[] num1 = sm.generateRandomNumber(n, rand);
            int[] num2 = sm.generateRandomNumber(n, rand);
            int[] result = sm.multiply(num1, num2);
            if (n <= 10) sm.printFinalResult(result);
            System.out.println(n + "\t" + sm.primitiveOps);
        }
    }
}

/*public class SimpleMultiplication {
    long primitiveOps = 0;

    public int[] multiply(int[] arr1, int[] arr2){
        int n = arr1.length;
        primitiveOps++;//assignment 

        int[] finalResult = new int[n + n];
        primitiveOps++;//assignment

        primitiveOps += (1 + (n + 1) + n);// for i=n-1; i>=0; i--
        for(int i=n-1; i>=0; i--){ //outer loop
            int carry = 0;
            primitiveOps += 1; //assignment

            primitiveOps += (1 + (n + 1) + n);//for j=n-1; j>=0; j--
            for(int j=n-1; j>=0; j--){
                int multi = arr1[j] * arr2[i] + carry + finalResult[i + j + 1];
                
                int partialProduct = multi % 10;
                carry = multi / 10;

                if (n <= 10) { // Only print step-by-step for small test cases
                    System.out.println(arr1[j] + " * " + arr2[i] + " -> Partial: " + partialProduct + ", Carrier: " + carry);
                }

                finalResult[i + j + 1] = partialProduct;

                primitiveOps += 16; //sum of ops above
            }
            finalResult[i] = carry;
            primitiveOps += 2; //index access and assignment
        }

        return finalResult;
    }

    public void printFinalResult(int[] finalResult){
        System.out.print("Final Result: ");
        boolean leadingZero = true;
        boolean allZeros = true; 

        for (int digit : finalResult) {
            if (leadingZero && digit == 0) continue;
            
            leadingZero = false;
            allZeros = false;
            System.out.print(digit);
        }

        if (allZeros) System.out.print(0); 
        System.out.println();
    }

    public static void main(String[] args){
        SimpleMultiplication sm = new SimpleMultiplication();
        java.util.Random rand = new java.util.Random();
        
        int n = 5; // Start small for testing (e.g., n=5) 
        int[] num1 = new int[n];
        int[] num2 = new int[n];

        // Generate random digits for the arrays 
        for (int i = 0; i < n; i++) {
            num1[i] = rand.nextInt(10);
            num2[i] = rand.nextInt(10);
        }

        // Run the multiplication [cite: 33]
        int[] result = sm.multiply(num1, num2);
        
        // Print final answer 
        sm.printFinalResult(result);
        
        // Output the total operations for the graph 
        System.out.println("Total Primitive Operations: " + sm.primitiveOps);
    }

}*/