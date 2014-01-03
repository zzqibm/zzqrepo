package com.ibm.dbm.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Path("Persons")
public class DbmBeanResource {

	//PS：使用Poster传递参数时必须形如这样的形式{"name":"a","age":2}
	//web.xml指定了com.sun.jersey.api.json.POJOMappingFeature为true，打开json转换功能
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Person add(Person a){
		
		System.out.println(a.getName());
		System.out.println(a.getAge());
		
		a.setAge(a.getAge() + 1);
		return a;
	}
	
	@DELETE
	@Path("delete/{name}")
	public String delete(@PathParam("name") String name){
		
		System.out.println("成功删除person: " + name);
		
		return "sucess";
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Person update(Person a){
		
		System.out.println(a.getName());
		System.out.println(a.getAge());
		
		a.setAge(a.getAge() - 1);
		return a;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person("a",1));
		persons.add(new Person("b",2));
		
		return persons;
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person findByName(@PathParam("name") String name) {
		return new Person(name, 2);
	}
} 

@JsonIgnoreProperties("age")
class Person{
	String name;
	int age;
	public Person() {
		
	}
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}