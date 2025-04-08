package emp.EMSystem.repository;

import emp.EMSystem.model.Attendance;
import emp.EMSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Repository
public interface AttendanceRepositery extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByEmployeeAndActiveTrue(Employee employee);
    List<Attendance> findByEmployee(Employee employee);
   // List<Attendance> findAllByActiveTrue();
    List<Attendance> findAllByActiveTrue();

}
