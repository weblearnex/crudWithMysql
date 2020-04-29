package com.crudWithMysql.crudWithMysql.utils;

import java.text.SimpleDateFormat;
import com.crudWithMysql.crudWithMysql.model.entity.Employee;
import com.crudWithMysql.crudWithMysql.model.form.EmployeeVo;
import com.google.gson.Gson;

public class Mapper {
	static SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
	static Gson gson = new Gson();
	public static Employee converFormVoToEntity(EmployeeVo employeeVo ) {
		System.out.println(employeeVo.getName());
		String json = gson.toJson(employeeVo);
		
		Employee employee = null;
		try {
		employee=	gson.fromJson(json, Employee.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	public static EmployeeVo converEntityToFormVo(Object result) {
		String json = gson.toJson(result);
		
		EmployeeVo employee = null;
		try {
		employee=	gson.fromJson(json, EmployeeVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	

}
