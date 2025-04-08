package emp.EMSystem.controller.attendcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import emp.EMSystem.dto.AttendanceDTO;
import emp.EMSystem.model.Attendance;
import emp.EMSystem.service.attendservice.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    public AttendanceService attendanceService;

    @PostMapping("/start")
    public ResponseEntity<String> startAttendance() {
        String message = attendanceService.startAttendance();
        return ResponseEntity.ok(message);
    }

    @PutMapping("/stop")
    public ResponseEntity<String> stopAttendance() {
        String message = attendanceService.stopAttendance();
        return ResponseEntity.ok(message);
    }

    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<List<AttendanceDTO>> getActiveEmployees() {
        return ResponseEntity.ok(attendanceService.getActiveAttendances());
    }

    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    @GetMapping("/all-attendance")
    public ResponseEntity<List<Attendance>> getAllAttendances(){
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

}
