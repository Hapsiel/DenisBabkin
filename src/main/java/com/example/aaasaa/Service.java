package com.example.aaasaa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class Service
{
    PersonRepository rep = new PersonRepository();
    //Вид объекта person в json
    record personRecord(int id, int age, String name){}
    //инициализирует списока в репозитории
    public void ServiceInit()
    {
        rep.initialize();
    }
    //преобразует полученный список персон в список json объектов
    public ArrayList<personRecord> toRecordList(ArrayList<PersonRepository.Person> personList)
    {
        ArrayList<personRecord> resultList = new ArrayList<personRecord>();
        personList.forEach((person -> resultList.add(new personRecord(person.id,person.age,person.name))));
        return resultList;
    }

    //Фильтрует список по возрасту
    public ArrayList<personRecord> GetFilteredByAge(int minAge, int maxAge)
    {
        Stream<PersonRepository.Person> personStream = rep.ReturnPersonList().stream();
        ArrayList<PersonRepository.Person> resultFilterList = personStream.filter(person -> person.age >= minAge)
                .collect(()->new ArrayList<PersonRepository.Person>(), // создаем ArrayList
                        (list, item)->list.add(item), // добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2)); // добавляем в список другой список)
        Stream<PersonRepository.Person> personStream2 = resultFilterList.stream();
        resultFilterList = personStream2.filter(person -> person.age <= maxAge)
                .collect(()->new ArrayList<PersonRepository.Person>(), // создаем ArrayList
                        (list, item)->list.add(item), // добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2)); // добавляем в список другой список)
        return toRecordList(resultFilterList);
    }
    public ArrayList<personRecord> NameSearch(String name)
    {
        Stream<PersonRepository.Person> personStream = rep.ReturnPersonList().stream();
        ArrayList<PersonRepository.Person> resultFilterList =  personStream.filter(person -> Objects.equals(person.name, name))
                .collect(()->new ArrayList<PersonRepository.Person>(),// создаем ArrayList
                        (list, item)->list.add(item),// добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2));// добавляем в список другой список)
        return toRecordList(resultFilterList);
    }
   public ArrayList<personRecord> SortByAge()
    {
        Stream<PersonRepository.Person> personStream = rep.ReturnPersonList().stream();
        ArrayList<PersonRepository.Person> resultFilterList =personStream.sorted(Comparator
                        .comparing(PersonRepository.Person::getAge))
                            .collect(()->new ArrayList<PersonRepository.Person>(),// создаем ArrayList
                                (list, item)->list.add(item),// добавляем в список элемент
                                    (list1, list2)-> list1.addAll(list2));// добавляем в список другой список)
        return toRecordList(resultFilterList);
    }
    public ArrayList<personRecord> getAll()
    {
        return toRecordList(rep.ReturnPersonList());
    }
    //Функция которая находит персоны по имени и изменяет первую
    public boolean ChangePerson(String oldName,int newAge, String newName)
    {
        int ListLenght = rep.PersonList.toArray().length;
        Stream<PersonRepository.Person> personStream = rep.ReturnPersonList().stream();
        ArrayList<PersonRepository.Person> resultFilterList =  personStream.filter(person -> Objects.equals(person.name, oldName))
                .collect(()->new ArrayList<PersonRepository.Person>(),
                    (list, item)->list.add(item),
                    (list1, list2)-> list1.addAll(list2));
        /*проверка что после фильтрации есть что изменять
        Как страно IDE говорит что код недостежим но при этом функция работает и код
        выполняется.*/
        if(resultFilterList.toArray().length != 0)
        {
        rep.ChangePerson(resultFilterList.get(0),newAge,newName);
        return ListLenght == rep.PersonList.toArray().length;
        }
        else return false;
    }
}