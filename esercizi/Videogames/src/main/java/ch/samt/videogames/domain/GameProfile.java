package ch.samt.videogames.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class GameProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
    @SequenceGenerator(name = "profile_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Min(1)
    private int livello;

    @Min(0)
    private int punti;

    @NotNull
    private LocalDate data_creazione;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
}