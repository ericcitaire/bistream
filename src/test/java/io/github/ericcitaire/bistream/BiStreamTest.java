package io.github.ericcitaire.bistream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

public class BiStreamTest {
    @Test
    public void factoryMethodReturnsNonNullInstance() throws Exception {
        Map.Entry<String, Integer> one = entry("One", 1);
        Map.Entry<String, Integer> two = entry("Two", 2);
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream
                = BiStream.of(Map.Entry::getKey, Map.Entry::getValue, one, two);

        assertThat(biStream).isNotNull();
    }

    @Test
    public void underlyingStreamContainsAllItems() throws Exception {
        Map.Entry<String, Integer> one = entry("One", 1);
        Map.Entry<String, Integer> two = entry("Two", 2);
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream
                = BiStream.of(Map.Entry::getKey, Map.Entry::getValue, one, two);

        assertThat(biStream.stream()).containsExactly(one, two);
    }

    @Test
    public void underlyingStreamContainsFilteredItems() throws Exception {
        Map.Entry<String, Integer> one = entry("One", 1);
        Map.Entry<String, Integer> two = entry("Two", 2);
        BiStream<Map.Entry<String, Integer>, String, Integer> biStream
                = BiStream.of(Map.Entry::getKey, Map.Entry::getValue, one, two)
                    .filter((name, value) -> value % 2 == 0);

        assertThat(biStream.stream()).containsExactly(two);
    }

    private static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
}
