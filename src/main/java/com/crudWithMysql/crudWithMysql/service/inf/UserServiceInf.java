package com.crudWithMysql.crudWithMysql.service.inf;


import org.springframework.http.ResponseEntity;
import com.crudWithMysql.crudWithMysql.model.entity.User;
import com.crudWithMysql.crudWithMysql.utils.ResponseBean;


public interface UserServiceInf  {
	
	ResponseEntity<ResponseBean> save(User employee);
	ResponseEntity<ResponseBean> getAll();
	ResponseEntity<ResponseBean> findByNameAndPassword(String name,String password);
	
	

}
