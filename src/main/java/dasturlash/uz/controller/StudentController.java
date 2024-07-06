package dasturlash.uz.controller;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.dto.StudentFilterDTO;
import dasturlash.uz.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        StudentDTO result = studentService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> result = studentService.getAllStudent();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<StudentDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "30") int size,
                                                       @RequestBody StudentFilterDTO filter) {
        PageImpl<StudentDTO> result = studentService.studentFilter(filter, page - 1, size);
        return ResponseEntity.ok(result);
    }
}
