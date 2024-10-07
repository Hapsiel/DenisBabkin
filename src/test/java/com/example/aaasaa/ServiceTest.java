package com.example.aaasaa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ServiceTest
{
    @Test
    void ServiceInitTest()
    {
        Service service = new Service();
        service.ServiceInit();
        int result = service.rep.PersonList.toArray().length;
        Assertions.assertEquals(result,5);
    }

    @Test
    void GetFilteredByAgeTest()
    {
        Service service = new Service();
        service.ServiceInit();
        int result = service.GetFilteredByAge(20,60).toArray().length;
        Assertions.assertEquals(2,result);
    }

    @Test
    void NameSearchTest()
    {
        Service service = new Service();
        service.ServiceInit();
        int result = service.NameSearch("Don").toArray().length;
        Assertions.assertEquals(1,result);
    }

    @Test
    void SortByAgeTest()
    {
        Service service = new Service();
        service.ServiceInit();
        int result = service.SortByAge().getFirst().age();
        Assertions.assertEquals(11,result);
    }

    @Test
    void GetAllTest()
    {
        Service service = new Service();
        service.ServiceInit();
        int result = service.getAll().toArray().length;
        Assertions.assertEquals(5,result);
    }

    @Test
    void ChangePersonTest()
    {
        Service service = new Service();
        service.ServiceInit();
        boolean result = service.ChangePerson("Don",28,"Dave");
        Assertions.assertTrue(result);
    }
}
