package emp.EMSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration;
    private boolean active;
    private String employeeName;
    private String employeeEmailId;
    private Integer employeeId;

    public AttendanceDTO(Long id, LocalDateTime startTime, LocalDateTime endTime,
               Long duration,          boolean active, String employeeName, String employeeEmailId , Integer employeeId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration=duration;
        this.active = active;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeEmailId=employeeEmailId;
    }
}
