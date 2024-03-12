package com.modelmapper.map.entity;




import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="posts",uniqueConstraints = {@UniqueConstraint(columnNames={"title"})})
public class Post {
    @Id

    private String id;
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Column(name = "content", nullable = false)
    private String content;
//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Comment> comments = new HashSet<>();
}
