package bekks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "readers")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reader_gen"
    )
    @SequenceGenerator(name = "reader_gen",
            sequenceName = "reader_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private int age;
    private String email;


    public Reader(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Reader(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
