package com.kraytsman.common;

public interface MyObservable {

    void add(MyObserver observer);
    void remove(MyObserver observer);
    void inform();

}
