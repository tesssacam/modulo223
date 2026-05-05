package ch.samt.videogames.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 30)
    private String cognome;

    @Min(value = 14, message = "Età minima 14")
    @Max(value = 50, message = "Età massima 50")
    private int eta;

    @NotBlank
    @Size(min = 3, max = 20)
    private String ruolo;

    @Min(1)
    @Max(99)
    private int numeroMaglia;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private GameProfile gameProfile;
}