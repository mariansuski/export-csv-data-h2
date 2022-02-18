package com.finture.adam.exportcsvdatah2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@Setter
@Getter
public class Tags {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId; // TODO consider other ids
    @Column(name = "movie_id")
    private int movieId; // TODO consider other ids
    @Column(name = "tag")
    private String tag;
    @Column(name = "timestamp_value")
    private String timestampValue;
}
