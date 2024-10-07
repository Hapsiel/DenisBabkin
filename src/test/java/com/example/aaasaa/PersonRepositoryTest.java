package com.example.aaasaa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PersonRepositoryTest
{
    @Test
    void InitializeTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        int currectLenght = repository.PersonList.toArray().length;
        Assertions.assertEquals(5,currectLenght);
    }

    @Test
    void HasDuplicateTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        boolean result = repository.HasDuplicate("Don");
        Assertions.assertTrue(result);
    }

    @Test
    void AddPersonTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.addPerson(23,"Jhon");
        PersonRepository.Person result = repository.PersonList.getFirst();
        Assertions.assertEquals("Jhon", result.name);
    }

    @Test
    void ReturnPersonListTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        ArrayList<PersonRepository.Person> result = repository.ReturnPersonList();
        Assertions.assertEquals(5,result.toArray().length);
    }

    @Test
    void DeletePerosnByNameTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        boolean result = repository.DeletePerosnByName("Don");
        Assertions.assertTrue(result);
    }

    @Test
    void DeletePerosnByIdTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        boolean result = repository.DeletePerosnById(2);
        Assertions.assertTrue(result);
    }

    @Test
    void ChangePersonTest()
    {
        PersonRepository repository = new PersonRepository();
        repository.initialize();
        repository.ChangePerson(repository.PersonList.getFirst(),1,"Dave");
        Assertions.assertEquals(repository.PersonList.getFirst().name,"Dave");
    }
}
