package com.model;

import com.model.common.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "subscriber")
public class Subscribers implements IEntity {

    @Id
    @SequenceGenerator(name = "subscriber_id", sequenceName = "subscriber_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    @Column(length = 5000)
    private String feedback;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;


}
