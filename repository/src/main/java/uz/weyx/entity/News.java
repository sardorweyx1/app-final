package uz.weyx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "news")
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 30)
    @Column(unique = true, nullable = false, columnDefinition = "text")
    private String title;

    @Size(min = 1, max = 255)
    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Author author;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp modified;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "news_tags",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    public void addTags(Set<Tag> tags){
        this.tags.addAll(tags);
        tags.forEach(tag -> tag.getNews().add(this));
    }
}
