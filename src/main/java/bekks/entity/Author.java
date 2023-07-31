package bekks.entity;

import enums.Gender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Table(name="authors")
@Setter
@Getter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "author_gen"
    )
    @SequenceGenerator(name="author_gen",
            sequenceName = "author_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    @Column(name="date_of_birth")
    private java.sql.Date dateOfBirth;
    private String country;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToMany(mappedBy = "authors"
    ,cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE
    }
    )
    private List<Publisher> publishers;
    @OneToMany(mappedBy = "author",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE}
            )
    private List<Book> books;
    public Author(Long id, String firstName, String lastName, String email, java.sql.Date dateOfBirth, String country, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.gender = gender;
    }

    public Author(String firstName, String lastName, String email, java.sql.Date dateOfBirth, String country, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                '}';
    }
}
