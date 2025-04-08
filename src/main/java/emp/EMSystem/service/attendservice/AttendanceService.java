package emp.EMSystem.service.attendservice;

import emp.EMSystem.model.Attendance;
import emp.EMSystem.model.Employee;
import emp.EMSystem.repository.AttendanceRepositery;
import emp.EMSystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepositery attendancerepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    // Get the currently logged-in Employee
    private Employee getLoggedInEmployee() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername(); // Get email from JWT token
            return employeeRepo.findByEmailId(email)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        }
        throw new RuntimeException("User not authenticated");
    }

    // Start Attendance (only for logged-in employee)
    public String startAttendance() {
        Employee employee = getLoggedInEmployee();
        Optional<Attendance> existingAttendance = attendancerepo.findByEmployeeAndActiveTrue(employee);

        if (existingAttendance.isPresent()) {
            return "Attendance already started.";
        }

        Attendance newAttendance = new Attendance(employee, LocalDateTime.now());
        newAttendance.setActive(true);
        attendancerepo.save(newAttendance);
        return "Attendance started for " + employee.getName();
    }

    // Stop Attendance (only for logged-in employee)
    public String stopAttendance() {
        Employee employee = getLoggedInEmployee();
        Optional<Attendance> attendanceOpt = attendancerepo.findByEmployeeAndActiveTrue(employee);

        if (attendanceOpt.isEmpty()) {
            return "No active attendance found.";
        }

        Attendance attendance = attendanceOpt.get();
        attendance.setEndTime(LocalDateTime.now());
        attendance.setActive(false);
        attendancerepo.save(attendance);

        return "Attendance stopped successfully for " + employee.getName();
    }

    // Get all Active Employees (only for HR/Admin)
    public List<Attendance> getActiveEmployees() {
        return attendancerepo.findAllByActiveTrue();
    }
}
