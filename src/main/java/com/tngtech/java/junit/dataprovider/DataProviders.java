package com.tngtech.java.junit.dataprovider;

import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    /**
     * Helper method to create an {@link Object} array containing all the given arguments, e.g.
     *
     * <pre>
     * <code>
     * Object[] a = $("test", 4);
     * </code>
     * </pre>
     *
     * @param args which should be contained in the resulting {@link Object} array
     * @return an {@link Object} array containing all the given {@code args}
     * @see #$$
     */
    public static Object[] $(Object... args) { // define it with <T> produces var-args warning on user side for java < 6
        return args;
    }

    /**
     * Helper method to create an array of the given {@link Object} arrays, e.g.
     *
     * <pre>
     * <code>
     * // @formatter:off
     * Object[][] b = $$(
     *          $("",        0),
     *          $("test",    4),
     *          $("foo bar", 7),
     *      );
     * // @formatter:on
     * </code>
     * </pre>
     *
     * @param args which should be contained in the resulting array of {@link Object} array
     * @return an array of {@link Object} arrays containing all the given {@code args}
     * @see #$
     */
    public static Object[][] $$(Object[]... args) {
        return args;
    }

    /**
     * Creates a dataprovider test for each argument.
     *
     * @param args which are wrapped in {@link Object} arrays and combined to {@link Object}{@code [][]}
     * @return an array which contains {@link Object} arrays for each single argument
     */
    public static Object[][] testForEach(Object... args) {
        Object[][] result = new Object[args.length][1];
        for (int idx = 0; idx < args.length; idx++) {
            result[idx][0] = args[idx];
        }
        return result;
    }

    /**
     * Creates a dataprovider test for each element in the given {@link Iterable}.
     *
     * @param <T> the type of elements returned by the given {@link Iterable}
     * @param args which are wrapped in {@link Object} arrays and combined to {@link Object}{@code [][]}
     * @return an array which contains {@link Object} arrays for each single element in the given {@link Iterable}
     * @throws NullPointerException iif given {@code args} is {@code null}
     */
    public static <T> Object[][] testForEach(Iterable<T> args) {
        if (args == null) {
            throw new NullPointerException("args must not be null");
        }

        List<T> list = new ArrayList<T>();
        for (T arg : args) {
            list.add(arg);
        }
        return testForEach(list.toArray());
    }

    /**
     * Creates a dataprovider test for each value in the given {@link Enum} class.
     *
     * @param <E> the type of the enum type subclass modeled by the given {@code Class}
     * @param enumClass for which each value is wrapped into an array of {@link Object} arrays
     * @return an array which contains {@link Object} arrays for each single value in the given {@link Enum}
     * @throws NullPointerException iif given {@code enumClass} is {@code null}
     */
    public static <E extends Enum<E>> Object[][] testForEach(Class<E> enumClass) {
        if (enumClass == null) {
            throw new NullPointerException("enumClass must not be null");
        }
        return testForEach((Object[]) enumClass.getEnumConstants());
    }
}
