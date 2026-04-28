package ch.samt.blog.domain;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.*;
        import lombok.Data;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "lunghezza tra 3 e 20 caratteri")
    private String title;

    @NotBlank(message = "scadenza carta obbligatoria")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Formato scadenza MM/YY")
    private String publicationDate;

    @NotBlank
    @Size(min = 2, max = 10, message = "lunghezza tra 3 e 20 caratteri")
    private String category;

    @NotBlank
    private String author;

    @NotNull
    @Min(0)  // non negativi
    private Integer likenumber;

    @NotBlank
    @Lob  // tipo CLOB (testo lungo) (punto 2)
    private String content;

}