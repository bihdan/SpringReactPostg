package org.example.spring_react_postg.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    // TODO: create 'notes' and use
//    @ManyToOne
//    @JoinColumn(name = "note_id", nullable = true)
//    private Note note;

    @Column(nullable = false)
    private String front;

    @Column(nullable = false)
    private String back;

    @Column(nullable = false)
    private String flag;

    private java.sql.Date endDate;

    private Integer daysJump;

    @Column(nullable = false, columnDefinition = "int default 10")
    private Integer ease;

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

