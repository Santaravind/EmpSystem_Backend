package emp.EMSystem.controller.document;


import emp.EMSystem.service.docservice.S3ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class S3Controller {

  @Autowired
   private S3ImageUploader uploader;

    //upload image
    @PostMapping("/profile/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file , @RequestParam("empID")  Long empId) throws IOException {
        return  ResponseEntity.ok(uploader.uploadImage(file,empId));
    }

    //getall request
    @GetMapping("/getall")
    public List<String> getAllFiles(){
        return uploader.allFiles();
    }

    //get url by name
    @GetMapping("/profile/{fileName}")
    public String urlByName(@PathVariable("fileName") String fileName) {

        return uploader.getImagetUrlByName(fileName);
    }

    //getUrl By empId
    @GetMapping("/profile/empId/{empId}")
    public String urlByEmpId(@PathVariable("empId") Long empId){
        return uploader.getFileUrlByEmpId(empId);
    }


}
