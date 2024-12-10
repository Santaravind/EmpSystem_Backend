package emp.EMSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "HR")
public class HR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" ,nullable = false ,updatable=false)
    private Integer hrId;

    private String first_Name;
    private String last_Name;
    private String emailId;
    private long phone_Number;
    private String date_of_Birth;
    private String address;
    private String joining_Date;
    private String position;
    private String status;
}
