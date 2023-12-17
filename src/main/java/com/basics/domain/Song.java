package com.basics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "songs")
@Entity
public class Song {
    
    private Long id;
    private String title;
    private String script;

    /*
     * putting annotations on getters to use Property @Access instead of Field @Access
     */
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_song"
    )
    @SequenceGenerator(
        name = "seq_song",
        sequenceName = "seq_song",
        allocationSize = 1
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title.toUpperCase();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "text")
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
