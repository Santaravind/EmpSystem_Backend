package emp.EMSystem.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private  Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private Employee employee;


    private LocalDateTime startTime;


    private  LocalDateTime endTime;

    private Long duration;

    private  boolean active;

    public Attendance(Employee employee, LocalDateTime startTime) {
        this.employee = employee;
        this.startTime = startTime;
        this.active = true; // Attendance starts as active
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        if(startTime != null && endTime != null){
            this.duration=Duration.between(startTime,endTime).toMinutes();

        }

    }
    public void setActive(boolean active) { this.active = active; }


}
