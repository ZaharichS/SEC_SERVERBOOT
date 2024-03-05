package reg.example.SecServer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publisher")
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "[А-Я][а-я]{1,20}")
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<BookEntity> books;
}
