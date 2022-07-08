
package net.javaguides.springboot.model;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.format.annotation.DateTimeFormat;

        import javax.persistence.*;
        import java.time.LocalDateTime;
        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emploeee")
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Id
    @Column(name = "provider_name")
    private String provider_name;

    @Column(name = "flow_name")
    private String flow_name;

    @Column(name = "downtime_from")
    public LocalDateTime downtime_from;

    @Column(name = "downtime_to")
    public LocalDateTime downtime_to;
}

//@Table(name = "kuch_bhi")
//public class Employee {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column(name = "email_id")
//    private String emailId;
//}