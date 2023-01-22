package com.restrepc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restrepc.entity.Student;
import com.restrepc.repository.StudentRepository;

@Service   //Esta clase está destinada como un servicio
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		System.out.println("Entra a getStudents del service");
//		return List.of(
//				new Student(120, "Karlos Edward", "karlos@restrepc.com", LocalDate.of(2000, Month.APRIL, 30), 23));
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		System.out.println("Estudiante!! \n "+student);	
		Optional<Student> studentMail =  studentRepository.findStudentByEmail(student.getEmail());
		if (studentMail.isPresent()) 
			throw new IllegalStateException("Email ya existe!");
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id))	 {
			throw new IllegalStateException("Estudiante con ID "+id+" NO existe");
		}
		studentRepository.deleteById(id);
	}
	
	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Estudiante con ID \"+id+\" NO existe"));
		System.out.println("Obtiene el estudiante llamado "+student.getName());
		if (name != null && name.length()>0 && !name.equals(student.getName())) {
			System.out.println("Adiciona el nombre");
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
			System.out.println("Adiciona el email");
			if (studentRepository.findStudentByEmail(email).isPresent())  {
				System.out.println("Se encontró un correo!!!");
				throw new IllegalStateException("Email ya existe!");
			}
			student.setEmail(email);
		}
//		studentRepository.save(student);
	}
	
}
