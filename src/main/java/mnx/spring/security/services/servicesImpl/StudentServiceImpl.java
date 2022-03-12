package mnx.spring.security.services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mnx.spring.security.models.dto.StudentDto;
import mnx.spring.security.models.vo.StudentVo;
import mnx.spring.security.repositories.StudentRepository;
import mnx.spring.security.services.servicesInterfaces.StudentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDto getStudentByRollNumber(Integer rollNumber) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<StudentVo> studentVoOptional = studentRepository.findByStudentRollNumber(rollNumber);
        final StudentDto[] studentDto = new StudentDto[1];
        studentVoOptional.ifPresentOrElse((student) -> {

            try {
                studentDto[0] = mapper.readValue(mapper.writeValueAsString(student), StudentDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }, () -> {
            System.out.println("Student by given roll number do not exists");
        });
        return studentDto[0];
    }

    @Override
    public StudentDto createNewStudent(StudentDto student) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        StudentVo toPersistStudentRequest = mapper.readValue(mapper.writeValueAsString(student), StudentVo.class);
        toPersistStudentRequest.setStudentId(UUID.randomUUID().toString());
        StudentVo createdStudent = studentRepository.save(toPersistStudentRequest);

        StudentDto createdStudentResponse = null;
        createdStudentResponse = mapper.readValue(mapper.writeValueAsString(createdStudent), StudentDto.class);
        return createdStudentResponse;
    }
}
