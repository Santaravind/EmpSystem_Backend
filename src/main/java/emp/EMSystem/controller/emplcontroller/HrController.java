package emp.EMSystem.controller.emplcontroller;

import emp.EMSystem.model.HR;
import emp.EMSystem.service.emplservice.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HrController {

    @Autowired
    private HrService hrService;

    @GetMapping("/hr")
    public ResponseEntity<List<HR>> getAllEmployee(){
        return  ResponseEntity.ok(hrService.getAllHrDetail());
    }

    @PostMapping("/hr")
    public ResponseEntity<HR> createEmployee(@RequestBody  HR hr ){
        return  ResponseEntity.ok(hrService.addHrDetail(hr));
    }
}
