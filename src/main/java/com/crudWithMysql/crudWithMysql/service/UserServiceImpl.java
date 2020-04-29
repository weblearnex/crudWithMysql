package com.crudWithMysql.crudWithMysql.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.crudWithMysql.crudWithMysql.exception.MscObjectSaveException;
import com.crudWithMysql.crudWithMysql.model.entity.User;
import com.crudWithMysql.crudWithMysql.reposatory.inf.UserReposatoryInf;
import com.crudWithMysql.crudWithMysql.service.inf.UserServiceInf;
import com.crudWithMysql.crudWithMysql.utils.ResponseBean;


@Service
public class UserServiceImpl implements  UserServiceInf {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserReposatoryInf userReposatoryInf;
	
	@Override
	public ResponseEntity<ResponseBean> save(User user) {
		try {
				logger.info("Entering inside of save method info");
				logger.debug("Entering inside of save method debug");
				userReposatoryInf.save(user);
				logger.info("existed inside of save method info");
				return new ResponseEntity<>(new ResponseBean(200,"Success",user),HttpStatus.OK);
		}catch(Exception ex) {
			throw new MscObjectSaveException("Error occured while save user",user);
		}
	
	}
	
	@Override
	public ResponseEntity<ResponseBean> getAll(){
		try {
			List<User> list = (List<User>) userReposatoryInf.findAll();
			return new ResponseEntity<>(new ResponseBean(200,"Success",list),HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new ResponseBean(500,"fail",ex.getMessage(),null),HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<ResponseBean> findByNameAndPassword(String name, String password) {
		try {
		List<User> list = (List<User>) userReposatoryInf.findByNameAndPassword(name, password);
		return new ResponseEntity<>(new ResponseBean(200,"Success",list),HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new ResponseBean(500,"fail",ex.getMessage(),null),HttpStatus.EXPECTATION_FAILED);
		}
	}

}
