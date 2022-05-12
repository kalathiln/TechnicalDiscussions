import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TechnicalDiscussion {

    public static void main(String[] args) {


        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee("10000"));
        employeeList.add(new Employee("25000"));
        employeeList.add(new Employee("13600"));
        employeeList.add(new Employee("16000"));

        Stream<Employee> employeeStream = employeeList.stream();

        String sum = employeeStream.map(employee ->  employee.salary)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum)
                .toString();

        System.out.println("==================================================");
        System.out.println("The sum is "+ sum);

        /* For numbers from 1 to 100
          find the odd ones
          square it
          sum it.
         */

        findOddSquareSumOfHundred(IntStream.rangeClosed(1, 100));


    }

    /**
     *
     * @param stream Instream passed as a parameter
     */
    private static void findOddSquareSumOfHundred(IntStream stream) {

//        IntStream printableStream = stream;

        System.out.println("==================================================");

//        stream.peek(s -> System.out.println(s));


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
