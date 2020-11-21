package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.SalaryRepository;
import com.cs322.ors.model.Salary;

@Service
public class SalaryService {
	
	@Autowired
	public SalaryRepository salaryRepository;

	public List<Salary> getAllSalary() {
		return salaryRepository.findAll();
	}

	public List<Salary> getSalaryByUser(long userId) {
		return salaryRepository.findByUser_Id(userId);
	}

	public void createSalary(Salary salary) {
		salaryRepository.save(salary);
	}

	public void updateSalary(@Valid Salary salary, long salaryId) {
		Optional<Salary> SalaryDB = this.salaryRepository.findById(salaryId);
		
		if (SalaryDB.isPresent()){
			salary.setId(salaryId);
			salaryRepository.save(salary);
		}
		
	}

	public void deleteDish(long salaryId) {
		salaryRepository.deleteById(salaryId);
	}
}
