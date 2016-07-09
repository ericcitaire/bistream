package io.github.ericcitaire.bistream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

public class BiStreamTest {
    static final Map.Entry<String, Integer> ONE = entry("One", 1);
    static final Map.Entry<String, Integer> TWO = entry("Two", 2);

    @Test
    public void factoryMethodReturnsNonNullInstance() throws Exception {
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream = entriesBiStream(ONE, TWO);

        assertThat(biStream).isNotNull();
    }

    @Test
    public void underlyingStreamContainsAllItems() throws Exception {
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream = entriesBiStream(ONE, TWO);

        assertThat(biStream.stream()).containsExactly(ONE, TWO);
    }

    @Test
    public void underlyingStreamContainsFilteredItems() throws Exception {
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream = entriesBiStream(ONE, TWO)
                .filter((name, value) -> value % 2 == 0);

        assertThat(biStream.stream()).containsExactly(TWO);
    }

    @Test
    public void mappedStreamContainsMappedItems() throws Exception {
        Stream<String> stream = entriesBiStream(ONE, TWO)
                .mapToObj((name, value) -> String.format("%s -> %d", name, value));

        assertThat(stream).containsExactly("One -> 1", "Two -> 2");
    }

    private BiStream<Map.Entry<String, Integer>, String, Integer> entriesBiStream(Map.Entry<String, Integer> one, Map.Entry<String, Integer> two) {
        return BiStream.of(Map.Entry::getKey, Map.Entry::getValue, one, two);
    }

    private static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
}
