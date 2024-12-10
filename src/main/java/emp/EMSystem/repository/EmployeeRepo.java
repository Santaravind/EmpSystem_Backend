package emp.EMSystem.repository;

import emp.EMSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//public interface EmployeeRepo extends JpaRepository <Employee,Long> {
//
//}
public interface EmployeeRepo extends CrudRepository<Employee,Long> {

}
