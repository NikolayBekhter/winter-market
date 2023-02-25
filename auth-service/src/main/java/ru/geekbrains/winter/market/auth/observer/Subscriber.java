package ru.geekbrains.winter.market.auth.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Subscriber implements Observer{
    private String name;

    @Override
    public void handleEvent(String username) {
        System.out.println("Уважаемый " + name + "\nЗарегистрировался новый пользователь:\n" + username +
                "\n========================================================\n");
    }
}
