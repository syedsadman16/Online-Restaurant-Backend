package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.TestRepository;
import com.cs322.ors.model.Test;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;

	public Test createTest(Test test) {
		return testRepository.save(test);
	}

	public List<Test> getAllTests() {
		return testRepository.findAll();
	}

	public Optional<Test> getTestById(long id) {
		return testRepository.findById(id);

	}

	public Test updateTest(long id, Test test) {
		Optional<Test> testDb = testRepository.findById(id);
		if (testDb.isPresent()) {
			Test testUpdate = testDb.get();
			testUpdate.setValue(test.getValue());
			testRepository.save(testUpdate);
			return testUpdate;
		}
		return null;
	}

	public void deleteTest(long id) {
		Optional<Test> testDb = this.testRepository.findById(id);

		if (testDb.isPresent()) {
			testRepository.delete(testDb.get());
		}

	}

}
