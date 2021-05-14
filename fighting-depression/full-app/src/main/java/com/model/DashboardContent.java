package com.model;

import com.model.common.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "dashboard_content")
public class DashboardContent implements IEntity {


    @Id
    @SequenceGenerator(name = "day_wise_content_id", sequenceName = "seq_day_wise_content", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_wise_content_id")
    private Long id;

    @Column(length = 20000)
    private String message;
    @Column(length = 20000)
    private String policy;
    @Column(length = 20000)
    private String terms;
    @Column(length = 20000)
    private String aboutUs;


}
