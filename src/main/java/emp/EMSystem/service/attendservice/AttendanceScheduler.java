package emp.EMSystem.service.attendservice;

import emp.EMSystem.model.Attendance;
import emp.EMSystem.repository.AttendanceRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceScheduler {

    @Autowired
    public AttendanceRepositery attendanceRepositery;

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void stopExpiredAttendance() {
        List<Attendance> activeAttendances = attendanceRepositery.findAllByActiveTrue();


        for (Attendance attendance : activeAttendances) {
            if (attendance.getStartTime().plusHours(1).isBefore(LocalDateTime.now())) {
                attendance.setEndTime(LocalDateTime.now().isAfter(attendance.getStartTime().plusHours(1)) ?
                        attendance.getStartTime().plusHours(1) : LocalDateTime.now());
                attendance.setActive(false);
                attendanceRepositery.save(attendance);
            }
        }

    }
}
