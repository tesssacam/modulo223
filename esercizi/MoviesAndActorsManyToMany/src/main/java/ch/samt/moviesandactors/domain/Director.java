package ch.samt.moviesandactors.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "director_seq")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @OneToMany(mappedBy = "director")
    private Set<Movie> movies;
}
