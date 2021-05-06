package com.model;

import com.model.common.IEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Getter
@Setter
@Entity
@Table(name = "stored_file")
public class StoredFile implements IEntity {

    @Id
    @SequenceGenerator(name = "stored_file_id", sequenceName = "seq_stored_file", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_file_id")
    private Long id;
    private String name;
    private String extension;
    private String description;

    private Blob content;

    @Transient
    private String content2;


}
