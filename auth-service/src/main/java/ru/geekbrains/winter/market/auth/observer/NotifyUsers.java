package ru.geekbrains.winter.market.auth.observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyUsers implements Observed{
    String username;

    List<Observer> subscribers = new ArrayList<>();

    public void newUser(String newUser) {
        this.username = newUser;
        notifyObservers();
    }

    public void removeUser(String removeUser) {
        this.username = removeUser;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o :
                subscribers) {
            o.handleEvent(this.username);
        }
    }
}
