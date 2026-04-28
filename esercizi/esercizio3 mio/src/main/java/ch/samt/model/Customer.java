package ch.samt.model;

import jakarta.validation.constraints.*;

public class Customer {

    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 2, max = 50, message = "Il nome deve avere tra 2 e 50 caratteri")
    private String name;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Inserire una email valida")
    private String email;

    @NotNull(message = "L'età è obbligatoria")
    @Positive(message = "L'età deve essere positiva")
    private Integer age;

    @NotBlank(message = "Il commento è obbligatorio")
    @Size(min = 5, max = 200, message = "Il commento deve essere lungo almeno 5 caratteri")
    private String comment;

    public Customer() {}

    public Customer(Long id, String name, String email, Integer age, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.comment = comment;
    }

    // getter e setter per tutti i campi
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}