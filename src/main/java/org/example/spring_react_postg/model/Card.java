package org.example.spring_react_postg.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

/**
 * Сутність, що представляє навчальну картку (flashcard).
 * Картка прив'язана до {@link Deck} і містить поля для змісту, складності повторення, дати доступності тощо.
 */
@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    /**
     * Унікальний ідентифікатор картки.
     */
    @Id
    private Integer id;

    /**
     * Колода, до якої належить ця картка.
     */
    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    // TODO: create 'notes' and use
    // @ManyToOne
    // @JoinColumn(name = "note_id", nullable = true)
    // private Note note;

    /**
     * Текст на лицьовій стороні картки.
     */
    @Column(nullable = false)
    private String front;

    /**
     * Текст на зворотному боці картки.
     */
    @Column(nullable = false)
    private String back;

    /**
     * Індикатор, що позначає статус картки (наприклад, кольором або категорією).
     */
    @Column(nullable = false)
    private String flag;

    /**
     * Дата, після якої картку можна буде знову повторити.
     */
    private Date endDate;

    /**
     * Кількість днів до наступного повторення картки.
     */
    private Integer daysJump;

    /**
     * Показник легкості для алгоритму інтервального повторення.
     * Визначає, як швидко збільшується інтервал між повтореннями.
     */
    @Column(nullable = false, columnDefinition = "int default 10")
    private Integer ease;

    // Геттери та сеттери згенеровано вручну (анотації @Getter і @Setter також є)
    // Але можуть бути потрібні для JPA або явного контролю

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDaysJump() {
        return daysJump;
    }

    public void setDaysJump(Integer daysJump) {
        this.daysJump = daysJump;
    }

    public Integer getEase() {
        return ease;
    }

    public void setEase(Integer ease) {
        this.ease = ease;
    }
}
