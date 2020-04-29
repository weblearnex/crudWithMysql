package com.crudWithMysql.crudWithMysql.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crudWithMysql.crudWithMysql.exception.MscException;
import com.crudWithMysql.crudWithMysql.exception.MscObjectSaveException;
import com.crudWithMysql.crudWithMysql.model.entity.Employee;
import com.crudWithMysql.crudWithMysql.reposatory.inf.EmployeeReposatoryInf;
import com.crudWithMysql.crudWithMysql.service.inf.EmployeeServiceInf;
import com.crudWithMysql.crudWithMysql.utils.ConstaintInfo;
import com.crudWithMysql.crudWithMysql.utils.DateAndTimeUtils;
import com.crudWithMysql.crudWithMysql.utils.ResponseBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements  EmployeeServiceInf {


	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Value("${env}")
	private String env;
	
	@Autowired
	private EmployeeReposatoryInf employeeReposatoryInf;
	
	@Override
	public ResponseEntity<ResponseBean> save(Employee employee) {
		try {
			logger.info("Entering inside of save method info");
			logger.debug("Entering inside of save method debug");
			employee.setDepartment(ConstaintInfo.DEPARTMENT);
			employee.setEntryDate(DateAndTimeUtils.getCurrentDateAndTime());
			//System.out.println(10/0);
			employee.setEnv(env);
			employeeReposatoryInf.save(employee);
			logger.info("existed inside of save method info");
		return new ResponseEntity<>(new ResponseBean(200,"Success",employee),HttpStatus.OK);
		}catch(Exception ex) {
			throw new MscObjectSaveException("Error occured while save employee",employee);
		}
	
	}

	@Override
	public ResponseEntity<ResponseBean> getAll(){
		try {
			List<Employee> list = (List<Employee>) employeeReposatoryInf.findAll();
			//return new ResponseEntity<>(new ResponseBean(200,"Success",(List<Employee>) employeeReposatoryInf.findAll()),HttpStatus.OK);
			return new ResponseEntity<>(new ResponseBean(200,"Success",list),HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new ResponseBean(500,"fail",ex.getMessage(),null),HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public Object deleteById(long id) {
		Optional<Employee> employee = employeeReposatoryInf.findById(id);
		Object obj=new Object();
		if(employee.isPresent()) {
			employeeReposatoryInf.delete(employee.get());
			obj = employee.get();
		}else {
			obj ="Please enter correct id";
		}
		return obj;
	}

	@Override
	public List<Employee> findByNameAndAddress(String name, String address) {
		return employeeReposatoryInf.findByNameAndAddress(name, address);
	}

	@Override
	public ResponseEntity<ResponseBean> findById(long id) {
		Optional<Employee> employee = employeeReposatoryInf.findById(id);
		Object obj = new Object();
		if(employee.isPresent()) {
			obj = employee.get();
			return new ResponseEntity<>(new ResponseBean(200,"Success",obj),HttpStatus.OK);
		}else {
			throw new MscException("No Record found by this id :",id);
		
		}
	}
	
	
}
