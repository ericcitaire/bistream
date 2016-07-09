package io.github.ericcitaire.bistream;

import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Stream;

public interface BiStream<T, C1, C2> {

    Stream<T> stream();

    BiStream<T, C1, C2> filter(BiPredicate<? super C1, ? super C2> filter);

    static <T, C1, C2> BiStream<T, C1, C2> of(Function<T, C1> comp1, Function<T, C2> comp2, T... items) {
        return new BiStreamImpl<>(comp1, comp2, Stream.of(items));
    }

    class BiStreamImpl<T, C1, C2> implements BiStream<T, C1, C2> {
        private Stream<T> stream;
        private final Function<T, C1> comp1;
        private final Function<T, C2> comp2;

        BiStreamImpl(Function<T, C1> comp1, Function<T, C2> comp2, Stream<T> stream) {
            this.stream = stream;
            this.comp1 = comp1;
            this.comp2 = comp2;
        }

        @Override
        public Stream<T> stream() {
            return stream;
        }

        @Override
        public BiStream<T, C1, C2> filter(BiPredicate<? super C1, ? super C2> filter) {
            this.stream = stream().filter(item -> filter.test(comp1.apply(item), comp2.apply(item)));
            return this;
        }
    }
}
