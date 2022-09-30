package uz.weyx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, max = 30)
    @Column(unique = true, nullable = false, columnDefinition = "text")
    private String title;

    @Size(min = 5, max = 255)
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

    @ManyToMany
    @JsonIgnore
    private Set<Tag> tag;

}
