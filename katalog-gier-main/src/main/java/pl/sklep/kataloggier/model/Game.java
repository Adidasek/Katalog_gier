package pl.sklep.kataloggier.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private String platform;

    private Double rating;

    @Column(length = 3000)
    private String description;

    private String imageUrl;
}
