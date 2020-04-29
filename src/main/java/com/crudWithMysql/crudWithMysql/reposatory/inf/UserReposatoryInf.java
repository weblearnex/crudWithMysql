package com.crudWithMysql.crudWithMysql.reposatory.inf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crudWithMysql.crudWithMysql.model.entity.User;


@Repository
public interface UserReposatoryInf  extends CrudRepository<User, Long> {
	
	List<User> findByNameAndPassword(String name,String password);

}
