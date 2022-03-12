package mnx.spring.security.services.servicesInterfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import mnx.spring.security.models.dto.StudentDto;

public interface StudentService {

    public StudentDto getStudentByRollNumber(Integer rollNumber);

    public StudentDto createNewStudent(StudentDto student) throws JsonProcessingException;

}
