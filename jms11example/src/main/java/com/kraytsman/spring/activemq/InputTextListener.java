package com.kraytsman.spring.activemq;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputTextListener implements Runnable, MyObservable {

    List<MyObserver> observers;
    Thread thread;
    Scanner scanner = new Scanner(System.in);
    String message;

    InputTextListener() {
        observers = new LinkedList<MyObserver>();
        System.out.println("Type message to send. Type \"exit\" to close program.");
    }

    void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        readTextFromConsole();
    }

    private void readTextFromConsole() {
        System.out.println("Type message:");
        String message = scanner.nextLine();
        if (!message.equals("exit")) {
            this.message = message;
            inform();
            readTextFromConsole();
        } else {
            thread.interrupt();
        }
    }

    @Override
    public void add(MyObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(MyObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void inform() {
        this.observers.forEach(MyObserver::update);
    }

    String getMessage() {
        return this.message;
    }

}
