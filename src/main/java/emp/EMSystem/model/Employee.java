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
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" ,nullable = false ,updatable=false)
    private Integer employeeID;

     @Column(name = "First_Name" ,nullable = false)
    private String first_Name;

     @Column(name = "Last_Name",nullable = false)
    private String last_Name;

     @Column(name = "EmailId", nullable = false)
    private String emailId;

     @Column(name = "Phone_Number" ,nullable = false)
    private long phone_Number;

     @Column(name = "DOB" ,nullable = false)
    private String date_of_Birth;

     @Column(name = "Address", nullable = false)
    private String address;

     @Column(name = "Joining_Date" ,nullable = false)
    private String joining_Date;

     @Column(name = "Department",nullable = false)
    private String department;

     @Column(name = "Position", nullable = false)
    private String position;

     @Column(name = "Status" ,nullable = false)
    private String status;


}
