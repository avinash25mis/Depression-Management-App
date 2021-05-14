package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.common.BaseEntity;
import com.model.common.IEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "day_wise_content")
public class DayWiseContent implements IEntity {

    @Id
    @SequenceGenerator(name = "day_wise_content_id", sequenceName = "seq_day_wise_content", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_wise_content_id")
    private Long id;

    private Integer day;
    private String time;
    private String title;
    @Column(length = 20000)
    private String message;
    private String genre1;
    private String genre2;
    private String link;
    private Boolean popup;
    @Transient
    private List<Long> docList;

   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name = "day_id")
   private List<StoredFile> fileList;



}
