package emp.EMSystem.controller.document;

import emp.EMSystem.model.Document;
import emp.EMSystem.service.docservice.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DocController {


    private final DocService service;
    @Autowired
    public DocController(DocService service) {
        this.service = service;
    }
    //not use
    @GetMapping("/database/get-all")
    public List<Document> getAllFilesname(){
        return service.getallfiles();
    }
    //not use
    @GetMapping("/database/file/{id}")
    public Optional<Document> getfilebyId(@PathVariable Long id){
        return  service.getFilebyId(id);
    }
    //not use
    @GetMapping("/database/filebyempId/{empId}")
    public ResponseEntity<String> getFileName(@PathVariable("empId") Long empId){
        String fileName= service.fetchFileNameByEmpId(empId);
        return ResponseEntity.ok(fileName);
    }

}
