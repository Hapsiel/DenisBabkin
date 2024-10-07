package com.example.aaasaa;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
public class crudPerson
    {
	    static Service service = new Service();
	    public static void main(String[] args)
        {

		    SpringApplication.run(crudPerson.class, args);
		    service.ServiceInit();
	    }


	@RestController
	public static class PersonController
	{

        //Эндпоинт для фильтрации по возрасту
		@GetMapping("/GetByAge")
		public ArrayList<Service.personRecord> GetByAge(@RequestParam(value = "minAge", defaultValue = "0") int minAge, @RequestParam(value = "maxAge", defaultValue = "100")int maxAge) throws JsonProcessingException {
			return service.GetFilteredByAge(minAge, maxAge);
		}
        //Эндпоинт для поиска по имени
		@GetMapping ("/SearchByName")
		public ArrayList<Service.personRecord> SearchByName(@RequestParam(value = "name", defaultValue = "Don") String name) throws JsonProcessingException {
			return service.NameSearch(name);
		}
        //Эндпоинт для получения полного списка отсортированного по age
		@GetMapping ("/SortByAge")
		public ArrayList<Service.personRecord> SortByAge() throws JsonProcessingException {
			return service.SortByAge();
		}
        //Эндпоинт для получения полного списка
		@GetMapping ("/GetAll")
		public ArrayList<Service.personRecord> GetAll() throws JsonProcessingException {
			return service.getAll();
		}
        //Эндпоинт для добавления новой персоны в список
		@GetMapping ("/Add")
		public String AddnewPerson(@RequestParam(value ="age", defaultValue = "0") int age,@RequestParam(value ="name", defaultValue = "Sten") String name) throws JsonProcessingException {
			if(service.rep.addPerson(age, name)) return "Success";
			else return "Failure";
		}
        //Эндпоинт для удаленния персоны по имени
		@GetMapping ("/DeleteByName")
		public String DeletePersonByName(@RequestParam(value ="name", defaultValue = "Sten") String name) throws JsonProcessingException {
			if(service.rep.DeletePerosnByName(name)) return "Success";
			else return "Failure";
		}
        //Эндпоинт для удаленния персоны по id
		@GetMapping ("/DeleteByID")
		public String DeletePersonById(@RequestParam(value ="id", defaultValue = "1") int id) throws JsonProcessingException {
			if(service.rep.DeletePerosnById(id)) return "Success";
			else return "Failure";
		}
        //Эндпоинт для измененния персоны ищет её по параметру oldname и заменяет её параметры на age и newName
		@GetMapping ("/ChangePerson")
		public String ChangePerson(@RequestParam(value ="age", defaultValue = "0") int age,@RequestParam(value ="newname", defaultValue = "Briar") String newname,@RequestParam(value ="oldname", defaultValue = "Don") String oldname) throws JsonProcessingException {
			if(service.ChangePerson(oldname,age,newname)) return "Success";
			else return "Failure";
		}
	}
}
