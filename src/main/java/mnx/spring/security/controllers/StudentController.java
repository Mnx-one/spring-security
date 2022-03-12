package mnx.spring.security.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import mnx.spring.security.models.dto.StudentDto;
import mnx.spring.security.models.vo.StudentVo;
import mnx.spring.security.services.servicesInterfaces.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(path = "/students/{rollNumber}")
    public ResponseEntity<StudentDto> getStudentByRollNumber(@PathVariable("rollNumber") Integer rollNumber){
        StudentDto student = studentService.getStudentByRollNumber(rollNumber);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(path = "/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) throws JsonProcessingException {
        studentService.createNewStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
