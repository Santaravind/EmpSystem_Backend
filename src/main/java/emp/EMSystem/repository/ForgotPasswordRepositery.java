package emp.EMSystem.repository;


import emp.EMSystem.model.ForgotPassword;
import emp.EMSystem.model.OurUsers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepositery extends JpaRepository<ForgotPassword,Integer> {

    @Query("SELECT fp FROM ForgotPassword fp WHERE fp.otp = ?1 AND fp.user = ?2")
    Optional<ForgotPassword>findByOtpAndUser(Integer otp, OurUsers  user);


    @Transactional
    @Modifying
    void deleteByUser(OurUsers user);

    Optional<ForgotPassword> findByUser(OurUsers user);


}
