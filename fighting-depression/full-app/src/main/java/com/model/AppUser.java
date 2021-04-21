package com.model;

import com.model.common.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author avinash.a.mishra
 */
@Entity
@Getter
@Setter
@Table(name="app_user")
public class AppUser implements IEntity {
    public AppUser() {
    }

    @Id
    @SequenceGenerator(name = "user_id", sequenceName = "seq_user_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String username;
    private String password;

    private String email;

   @Column(name="first_name")
   private String firstName;
   @Column(name="last_name")
   private String lastName;

   private boolean active;



}
