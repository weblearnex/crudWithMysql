package com.crudWithMysql.crudWithMysql.service.inf;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.crudWithMysql.crudWithMysql.model.entity.Employee;
import com.crudWithMysql.crudWithMysql.utils.ResponseBean;


public interface EmployeeServiceInf {
	
	ResponseEntity<ResponseBean> save(Employee employee);

	ResponseEntity<ResponseBean> getAll();
	
	Object deleteById(long id);

	List<Employee> findByNameAndAddress(String name,String address);
	
	ResponseEntity<ResponseBean> findById(long id);
}
