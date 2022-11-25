package com.example.FlightsApplication.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CacheElement<K,V> {
    private K key;
    private V value;
}
