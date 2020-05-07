package com.linear.mQueue;

interface Iterator<E> {
    boolean hasNext();

    E next();

    void remove();
}

