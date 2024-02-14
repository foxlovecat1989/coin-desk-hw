package com.ed.coindesk.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "currency")
public class Currency {
    @Id
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
