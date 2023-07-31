package bekks.entity;

import enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="books")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "book_gen"
    )
    @SequenceGenerator(
            name="book_gen",
            sequenceName = "book_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String country;
    private int published_year;
    private int price;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @ManyToOne(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE
    }
    )
    private Publisher publishers;
    @ManyToOne(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    }
    )
    private Author author;
    @OneToOne(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    }
    )
    private Reader reader;
    public Book(Long id, String name, String country, int published_year, int price, Genre genre) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.published_year = published_year;
        this.price = price;
        this.genre = genre;
    }

    public Book(String name, String country, int published_year, int price, Genre genre) {
        this.name = name;
        this.country = country;
        this.published_year = published_year;
        this.price = price;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", published_year=" + published_year +
                ", price=" + price +
                ", genre=" + genre +
                '}';
    }
}
