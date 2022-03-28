import javax.persistence.*;

/*
 *  Table in the db.
 * create table student3 (id integer, name varchar(25), marks integer);
 */

@Entity
@Table(name = "STUDENT3")

public class student3 {
    student3()
    {

    }
    public student3(String name2, int marks2) {
        name = name2;
        marks = marks2;
    }

    @Id @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "marks")
    int marks;
}
