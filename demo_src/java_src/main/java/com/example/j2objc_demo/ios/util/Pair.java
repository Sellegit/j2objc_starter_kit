package com.example.j2objc_demo.ios.util;

import java.io.Serializable;

public class Pair<K,V> implements Serializable {

    private K left;

    public K getLeft() { return left; }

    private V right;

    public V getRight() { return right; }


    public Pair(K key, V value) {
        this.left = key;
        this.right = value;
    }

    @Override
    public String toString() {
        return left + "=" + right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() * 13 + (right == null ? 0 : right.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (left != null ? !left.equals(pair.left) : pair.left != null) return false;
            if (right != null ? !right.equals(pair.right) : pair.right != null) return false;
            return true;
        }
        return false;
    }
}

