package emp.EMSystem.service.docservice;

import emp.EMSystem.model.Document;
import emp.EMSystem.repository.DocumentRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocService {

    @Autowired
    private DocumentRepositery repositery;

    //find all data
    public List<Document> getallfiles(){
        return repositery.findAll();
    }

    //find by id
    public Optional<Document> getFilebyId(Long id){
        return repositery.findById(id);
    }

    public String fetchFileNameByEmpId(long empId){
        return  repositery.getFileNameByEmpId(empId);
    }

}
