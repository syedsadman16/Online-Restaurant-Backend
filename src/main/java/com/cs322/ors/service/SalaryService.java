package com.cs322.ors.service;

import java.math.BigDecimal;
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

	public void updateSalary(BigDecimal amount, long userId) {
		List<Salary> SalaryDB = this.salaryRepository.findByUser_Id(userId);
		
		if (SalaryDB.isEmpty() == false){
			SalaryDB.get(0).setAmount(amount);
			salaryRepository.save(SalaryDB.get(0));
		}
		
	}
}
