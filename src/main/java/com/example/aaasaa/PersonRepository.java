package com.example.aaasaa;

import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;


public class PersonRepository {
    @Data
    public static class Person
    {
        int id;
        int age;
        String name;

        public Person(int id, int age, String name)
        {
            this.id = id;
            this.name = name;
            this.age = age;
        }

    }

    int IdCounter = 0;// Счётчик количества объектов Person в PersonList необходим для присвоения id
    ArrayList<Person> PersonList = new ArrayList<>();

    // Проверяет объект Person на наличие совпадений по name если находит возвращает true
    public boolean HasDuplicate(String name) {
        Stream<Person> personStream = PersonList.stream();
        return personStream.anyMatch(person -> Objects.equals(person.name, name));
    }

    //создаёт объект Person в PersonList если нет дубликатов возвращает true если удалось
    public boolean addPerson(int age, String name) {
        int ListLenght = PersonList.toArray().length;//запоминаем длинну списка
        if (!HasDuplicate(name)) {
            IdCounter++;
            PersonList.add(new Person(IdCounter, age, name));
        }
        return ListLenght != PersonList.toArray().length;
    }

    // функция для добавления статовых пользователей, затычка что бы просто при старте программы список не был пуст
    public void initialize() {
        addPerson(23, "Garfield");
        addPerson(67, "Jeane");
        addPerson(11, "Don");
        addPerson(18, "Ger");
        addPerson(27, "Stason");
    }

    //Возвращает список PersonList
    public ArrayList<Person> ReturnPersonList()
    {
        return PersonList;
    }

    //Ищет Person по параметру name если находит удаляет
    public boolean DeletePerosnByName(String name)
    {
        int ListLenght = PersonList.toArray().length;//запоминаем длинну списка
        Stream<Person> personStream2 = PersonList.stream();

        PersonList = personStream2.filter(person -> !Objects.equals(person.name, name))
                .collect(()->new ArrayList<Person>(), // создаем ArrayList
                        (list, item)->list.add(item), // добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2)); // добавляем в список другой список)
        return ListLenght != PersonList.toArray().length;
    }
    //Фильтрует список удаляя все персоны с которые имеют заданный id
    public boolean DeletePerosnById(int id)
    {
        int ListLenght = PersonList.toArray().length;//запоминаем длинну списка
        Stream<Person> personStream2 = PersonList.stream();

        PersonList = personStream2.filter(person -> !Objects.equals(person.id, id))
                .collect(()->new ArrayList<Person>(), // создаем ArrayList
                        (list, item)->list.add(item), // добавляем в список элемент
                        (list1, list2)-> list1.addAll(list2)); // добавляем в список другой список)
        return ListLenght != PersonList.toArray().length;
    }
    //Изменяет полученную персону
    public void ChangePerson(Person person, int newAge, String newName)
    {
        person.age = newAge;
        person.name = newName;
    }
}

