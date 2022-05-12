import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAPIAndLambda {

    public static void main(String[] args) {

        /*
        Create a List of Employees with a salary String field
        Get the integer value to it and find the total cost for the employer in wages.
         */

        findTotalSalaryCost();

        /* For numbers from 1 to 100
          find the odd ones
          square it
          sum it.
         */

        findOddSquareSumOfHundred(IntStream.rangeClosed(1, 100));

        /*
            Use Steams.of in order to create a stream of object,
            and use lambda operations on the stream to find the first element of the stream

         */
        findFirstUsingStreamOf();

        // Stream from Arrays, sort, filter and print

        printAllTheElementsOfArraysStartingWithR();

        // Print the squares of an int Array and display the average.

        printAverageOfSquaresOfIntsInArray();

        // read a csv file and check for the validity of content in them

        readCsvCheckForValidity();

        //Usage of Instream and summary statistics

        printSummaryStatistics();



    }

    private static void printSummaryStatistics() {

        IntSummaryStatistics summaryStats = IntStream.range(1, 99).summaryStatistics();
        System.out.println("======================================\nPrinting Summary stats of the 1st 100 Numbers : \n================================");
        System.out.println(summaryStats);
        System.out.println("Average of 1 to 99 : "+summaryStats.getAverage());
    }

    private static void readCsvCheckForValidity() {

        try {
            Stream<String> rows = Files.lines(Paths.get("Data.txt"));


//            int count = (int)
                    rows.map(x->x.split(","))
                    .filter(r -> r.length ==3)
                    .filter(r-> Integer.parseInt(r[1]) > 12 )
                    .forEach(x -> System.out.println(x[0]+" : "+x[1]+" : "+x[2]) );
                    rows.close();
//                    .count();

            System.out.println("====================================================\n"+"Printing Values based on Validity Conditions : \n");
            Stream<String> rows1 = Files.lines(Paths.get("Data.txt"));
            rows1.map(r -> r.split(","))
                    .filter(r-> r.length ==3 )
                    .filter(r -> Double.parseDouble((r[2])) <= 1.4)
                    .forEach(r -> System.out.println(r[0]+" : "+r[1]+" : "+r[2]));

            rows1.close();
            System.out.println("Printing number of valid inputs in Data.tx[Containing 3 values per row] : "+ "\n===================================");
//            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printAverageOfSquaresOfIntsInArray() {
        System.out.println("\n\n"+"Printing Average of Squares of integers from an array : "+"\n"+"----------------------------");
        Arrays.stream(new int[] {2,4,6,8,10})
                .map(s->s*s)
                .average()
                .ifPresent(System.out::println);


    }

    private static void printAllTheElementsOfArraysStartingWithR() {


        System.out.println("Printing Names starting with R"+"\n"+"===============================");
        String[] names = {"Nikhil","Ravi","Pari", "Raju", "Bejoi", "RamaKrishnan", "Anusha","R15"};
        Arrays.stream(names).sorted().filter(s -> s.startsWith("R")).forEach(System.out::println);
    }

    private static void findFirstUsingStreamOf() {
        System.out.println("Printing the first element from a stream.of function :");
        System.out.println(
                "==================================================="
        );
        Stream.of("Nikhil", "Anusha","Ankit")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);
        System.out.println("\n\n"+"---------------------------------");

    }

    private static void findTotalSalaryCost() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee("10000"));
        employeeList.add(new Employee("25000"));
        employeeList.add(new Employee("13600"));
        employeeList.add(new Employee("16000"));

        Stream<Employee> employeeStream = employeeList.stream();
        /*
            Using a map to parse the String to Integer
            reduce used to get the sum
         */
        String sum = employeeStream.map(employee ->  employee.salary)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum)
                .toString() ;

        System.out.println("==================================================");
        System.out.println("The sum is "+ sum);

    }

    /**
     *
     * @param stream Instream passed as a parameter
     */
    private static void findOddSquareSumOfHundred(IntStream stream) {

        System.out.println("==================================================");

        int oddSQuareSumOfHundred = stream
                .filter(s->s%2 !=0)
                .peek(System.out::println)
                .map(s->s*s)
                .peek(System.out::println)
                .sum();

        System.out.println("==================================================");
        System.out.println("Odd Square Sum Of 5 : "+oddSQuareSumOfHundred);

    }

}
