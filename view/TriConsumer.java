package view;

/**
 * Functional interface like java.util.function.BiConsumer but with three
 * arguments.
 *
 * @author Eva Moniz
 * @param <T>
 * @param <U>
 * @param <V>
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {

    public void accept(T t, U u, V v);
}
