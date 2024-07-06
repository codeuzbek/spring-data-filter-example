package dasturlash.uz.service;

import dasturlash.uz.dto.StudentDTO;
import dasturlash.uz.dto.StudentFilterDTO;
import dasturlash.uz.entity.StudentEntity;
import dasturlash.uz.repository.StudentCustomRepository;
import dasturlash.uz.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCustomRepository studentCustomRepository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());

        studentRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public List<StudentDTO> getAllStudent() {
        Iterable<StudentEntity> entityList = studentRepository.findAll();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public PageImpl<StudentDTO> studentFilter(StudentFilterDTO filter, int page, int size) {
        Page<StudentEntity> result = studentCustomRepository.filter(filter, page, size);

        long totalCount = result.getTotalElements();
        List<StudentEntity> entityList = result.getContent();

        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setBirthdate(entity.getBirthdate());
            dtoList.add(dto);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<StudentDTO>(dtoList, pageRequest, totalCount);
    }
}
