package io.github.ericcitaire.bistream;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

public class MapStreamTest {
    static final Map.Entry<String, Integer> ONE = entry("One", 1);
    static final Map.Entry<String, Integer> TWO = entry("Two", 2);

    @Test
    public void factoryMethodReturnsNonNullInstance() throws Exception {
        MapStream<String, Integer> mapStream = MapStream.of(ONE, TWO);

        assertThat(mapStream).isNotNull();
    }

    private static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
}
