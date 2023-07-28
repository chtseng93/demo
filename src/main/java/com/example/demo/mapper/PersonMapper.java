package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Person;

@Mapper
public interface PersonMapper {
	
	@Results({
		@Result(property = "lastName", column = "last_name"),
		@Result(property = "firstName", column = "first_name"),
	})
	@Select("select * from person")
	List<Person> findAll();
	
	@Delete("delete from person where id = #{id}")
	 void deleteEmpolyeeById(Integer id);
	
	@Results({
		@Result(property = "lastName", column = "last_name"),
		@Result(property = "firstName", column = "first_name"),
	})
	@Select("select * from person where id = #{id}")
	Person getEmpolyeeById(Integer id);
	
	@Update("update person set last_name= #{lastName}, first_name= #{firstName}, email= #{email} where id=#{id}")
	public void updatePerson(Person person);

	@Insert(" insert into person (last_name,first_name,address,city,email) values(#{lastName},#{firstName},'XXXXX','Tapei',#{email})")
	void insertPerson(Person person);
	

}
