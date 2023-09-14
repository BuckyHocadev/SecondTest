package com.example.SignUpPage.Repository.verification;
import com.example.SignUpPage.Model.verification.VerificationCodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCodeModel, Long> {
    @Query(value = "SELECT * FROM verification_messages  WHERE phone_number = ?1 ORDER BY time DESC LIMIT 1", nativeQuery = true)
    Optional<VerificationCodeModel> findByPhoneNumberIfExists(String phoneNumber);
}
