package com.kraytsman.spring.activemq;

public interface MyObservable {

    void add(MyObserver observer);
    void remove(MyObserver observer);
    void inform();

}
