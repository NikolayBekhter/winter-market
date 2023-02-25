package ru.geekbrains.winter.market.auth.observer;

import org.springframework.stereotype.Component;

@Component
public interface Observer {
    public void handleEvent(String username);
}
