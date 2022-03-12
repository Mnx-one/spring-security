package mnx.spring.security.repositories;

import mnx.spring.security.models.vo.StudentVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentVo, String> {

    public Optional<StudentVo> findByStudentRollNumber(Integer studentRollNumber);

}
