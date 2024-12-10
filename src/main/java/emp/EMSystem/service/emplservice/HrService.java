package emp.EMSystem.service.emplservice;

import emp.EMSystem.model.HR;
import emp.EMSystem.repository.HRrepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrService {
    @Autowired
   private HRrepositery hrRepo;


    public List<HR> getAllHrDetail() {
        return (List<HR>)hrRepo.findAll();
    }

    public HR addHrDetail(HR hr) {
        return  hrRepo.save(hr);
    }
}
