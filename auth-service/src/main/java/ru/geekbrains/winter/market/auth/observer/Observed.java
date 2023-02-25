package ru.geekbrains.winter.market.auth.observer;

import org.springframework.stereotype.Component;

@Component
public interface Observed {
    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
