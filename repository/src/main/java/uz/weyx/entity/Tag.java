package uz.weyx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tags")
@Table(name = "tags")
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3,max = 55)
    @Column(nullable = false,unique = true,columnDefinition = "text")
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<News> news = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
