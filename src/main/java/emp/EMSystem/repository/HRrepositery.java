package emp.EMSystem.repository;

import emp.EMSystem.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRrepositery extends JpaRepository<HR,Integer> {
}
