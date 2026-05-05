package ch.samt.videogames.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "nome tra 2 e 30 caratteri")
    private String nome;

    @NotBlank
    @Size(min = 2, max = 30, message = "nome tra 2 e 30 caratteri")
    private String citta;

    @NotNull
    private LocalDate dataFondazione;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
