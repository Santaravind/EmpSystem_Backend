package emp.EMSystem.repository;

import emp.EMSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepo extends CrudRepository<Employee,Long> {


   @Query("SELECT e FROM Employee e WHERE LOWER(e.department) = LOWER(:department)")
   List<Employee> findByDepartment(@Param("department") String department);

}
