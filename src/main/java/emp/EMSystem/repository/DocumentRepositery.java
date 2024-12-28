package emp.EMSystem.repository;

import emp.EMSystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepositery extends JpaRepository<Document,Long> {


    @Query("SELECT d.fileName FROM Document d WHERE d.empId = ?1")
    public String getFileNameByEmpId(long empId);
}
