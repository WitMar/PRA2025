package functionsAndLambdas;

import java.util.function.Function;

/**
 * Lambda expression is just a for of defining function as in mathematics.
 * We put argument on the left side of the function and function body on the right side.
 * The equivalence symbol is exchange for ->.
 * Lambda: arguments -> function_body
 */
public class Functions {

    public static void main(String[] args) {

        //APPLY
        Function<Integer, Double> half = a -> a / 2.0;
            // apply the function to get the result
        System.out.println(half.apply(10));

        //andTHEN
        Function<Integer, Double> half_2 = a -> a / 2.0;
            // Now treble the output of half function
        half_2 = half_2.andThen(a -> 3 * a);
            // apply the function to get the result
        System.out.println(half_2.apply(10));

        //andTHEN
        Function<Integer, Double> half_3 = a -> a / 2.0;
        Function<Double, Double> half_4 = a -> a / 2.0;
            // Now treble the output of halffunction
        half_3 = half_3.andThen(half_4);
            // apply the function to get the result
        System.out.println(half_3.apply(10));

        // Identity function
        half_3 = half_3.andThen(Function.identity());
        System.out.println(half_3.apply(10));
    }

}
