package emp.EMSystem.controller.emplcontroller;


import emp.EMSystem.model.Employee;
import emp.EMSystem.service.emplservice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService service;


    @GetMapping("/get-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(service.getAllEmployee());
    }

    @PostMapping("/add-employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.addEmployee(employee));
    }

    @GetMapping("/get-employees/{id}")
    public ResponseEntity<Employee> getEmployeeBYId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }


    @DeleteMapping("/delete-employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id) {
        Employee empObj = service.getEmployeeById(id);
        String DeleteMsg = null;
        if (empObj != null) {
            DeleteMsg = service.deleteEmployee(empObj);
        }
        return ResponseEntity.ok(DeleteMsg);
    }

    @GetMapping("/employee/{department}")
    public ResponseEntity<List<Employee>> getByDepartment(@PathVariable("department") String department) {
        return ResponseEntity.ok(service.getbyDepartment(department));
    }


   //paging
    @GetMapping("/employees/{offset}/{pageSize}")
    public ResponseEntity<Page<Employee>> getEmployeeWithpagination(@PathVariable int offset, @PathVariable int pageSize) {
        // Page<Employee> employeePage=service.findEmployeeWithPagination(offset, pageSize);
        return ResponseEntity.ok(service.findEmployeeWithPagination(offset, pageSize));

    }
    //Sorting and paging
    @GetMapping("/employees/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Employee>> getEmployeeSortwithPaging(@PathVariable int offset, @PathVariable int pageSize,@RequestParam(defaultValue = "employeeID") String filed) {
         return ResponseEntity.ok(service.findPaginationwithSort(offset, pageSize,filed));
    }


}