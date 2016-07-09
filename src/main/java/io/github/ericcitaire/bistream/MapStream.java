package io.github.ericcitaire.bistream;

import java.util.Map;
import java.util.stream.Stream;

public interface MapStream<K, V> extends BiStream<Map.Entry<K, V>, K, V> {
    static <K, V> MapStream<K, V> of(Map.Entry<K, V>... entries) {
        return new MapStreamImpl<>(Stream.of(entries));
    }

    class MapStreamImpl<K, V> extends BiStreamImpl<Map.Entry<K, V>, K, V> implements MapStream<K, V> {
        public MapStreamImpl(Stream<Map.Entry<K, V>> stream) {
            super(Map.Entry::getKey, Map.Entry::getValue, stream);
        }
    }
}
