package emp.EMSystem.service.emplservice;

import emp.EMSystem.model.Employee;
import emp.EMSystem.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    //Find All Employee
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepo.findAll();
    }

    //Adding the employee
    public Employee addEmployee(Employee employee ){
        return  employeeRepo.save(employee);
    }



    public Employee getEmployeeById(Long id){
        return  employeeRepo.findById(id).get();
    }

    //Delete Employee
    public  String deleteEmployee(Employee employee){
        employeeRepo.delete(employee);
        return "Deletion is succesfully completed !!!"+employee.getEmployeeID();
    }
}
