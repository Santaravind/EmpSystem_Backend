package emp.EMSystem.repository;

import emp.EMSystem.model.OurUsers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<OurUsers,Integer> {

    Optional<OurUsers> findByEmail(String email);


    //for forgot password

    @Transactional
    @Modifying
   // @Query("update Users u set u.password=?2 where u.email=?1")
    @Query("UPDATE OurUsers u SET u.password = :password WHERE u.email = :email")
    void updatePassword(@Param("email") String email, @Param("password") String password);

}
