package bekks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="publishers")
@Setter @Getter
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "publisher_gen"
    )
    @SequenceGenerator(
            name="publisher_gen",
            sequenceName = "publisher_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String address;
    @ManyToMany(
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
//            CascadeType.REMOVE
    }
    )
    private List<Author> authors;

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @OneToMany(mappedBy = "publishers"
            ,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
//                    CascadeType.REMOVE
            }
            )
    private List<Book> books;

    public Publisher(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
