package ru.sicampus.bootcamp2026.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sicampus.bootcamp2026.dto.PersonDTO;
import ru.sicampus.bootcamp2026.entity.Department;
import ru.sicampus.bootcamp2026.entity.Person;
import ru.sicampus.bootcamp2026.exception.DepartmentNotFoundException;
import ru.sicampus.bootcamp2026.exception.PersonNotFoundException;
import ru.sicampus.bootcamp2026.repository.DepartmentRepository;
import ru.sicampus.bootcamp2026.repository.PersonRepository;
import ru.sicampus.bootcamp2026.service.PersonService;
import ru.sicampus.bootcamp2026.util.PersonMapper;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<PersonDTO> getAllPersons() {
//        List<Person> all = personRepository.findAll();
//        List<PersonDTO> res = new ArrayList<>();
//        for (Person person : all) {
//            res.add(PersonMapper.convertToDto(person));
//        }
//        return res;
        return personRepository.findAll().stream()
                .map(PersonMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(PersonMapper::convertToDto)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    @Override
    public PersonDTO createPerson(PersonDTO dto) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(dto.getName());
        if (optionalDepartment.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found");
        }
        Person person = new Person();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl(dto.getPhotoUrl());
        person.setDepartment(optionalDepartment.get());

        return PersonMapper.convertToDto(personRepository.save(person));
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO dto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));

        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhotoUrl(dto.getPhotoUrl());

        return PersonMapper.convertToDto(personRepository.save(person));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
