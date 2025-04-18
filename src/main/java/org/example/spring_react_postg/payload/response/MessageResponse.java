package org.example.spring_react_postg.payload.response;

/**
 * Відповідь повідомлення, яка використовується для передачі текстового повідомлення в API.
 * Зазвичай використовується для інформування користувача про результат операцій.
 */
public class MessageResponse {

    /**
     * Текст повідомлення.
     */
    private String message;

    /**
     * Конструктор для ініціалізації об'єкта з повідомленням.
     *
     * @param message текст повідомлення
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Повертає текст повідомлення.
     *
     * @return текст повідомлення
     */
    public String getMessage() {
        return message;
    }

    /**
     * Встановлює текст повідомлення.
     *
     * @param message нове повідомлення
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
