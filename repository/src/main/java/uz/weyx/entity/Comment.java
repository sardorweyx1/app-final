package uz.weyx.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3,max = 255)
    @Column(nullable = false,columnDefinition = "text")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private News news;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp created;


    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp modified;
}
