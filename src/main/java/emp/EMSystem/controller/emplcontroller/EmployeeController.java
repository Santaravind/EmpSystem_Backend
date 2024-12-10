package emp.EMSystem.controller.emplcontroller;


import emp.EMSystem.model.Employee;
import emp.EMSystem.service.emplservice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping()
public class EmployeeController {
    @Autowired
  private EmployeeService service;


    @GetMapping("/user/get-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return  ResponseEntity.ok(service.getAllEmployee());
    }

    @PostMapping("/user/add-employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody  Employee employee){
        return  ResponseEntity.ok(service.addEmployee(employee));
    }

    @GetMapping("/user/get-employees/{id}")
    public ResponseEntity<Employee>getEmployeeBYId(@PathVariable("id") Long id){
        return  ResponseEntity.ok(service.getEmployeeById(id));
    }


    @DeleteMapping("/user/delete-employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id){
        Employee empObj=service.getEmployeeById(id);
        String DeleteMsg=null;
        if(empObj!=null){
            DeleteMsg=service.deleteEmployee(empObj);
        }
        return ResponseEntity.ok(DeleteMsg);
    }





}
