package fluentAPI;

import fluentAPI.interfaces.Title;
import fluentAPI.model.Person;
import fluentAPI.model.PersonBuilder;

import java.util.List;

public class MainFluentApi {

    public static void main(String[] args) {

        //Package-private constructor is not accessible here, we must use the builder:
        //Person p = new Person("a", Title.PROF);

        PersonBuilder personMaker = new PersonBuilder();
        Person marcin = personMaker.withName("Marcin").withTitle(Title.DR).build();

        Person donald = personMaker.withName("Donald").withTitle(Title.STUDENT).build();
        Person karol = personMaker.withName("Karol").withTitle(Title.PROF).build();

        marcin.addFriend(donald).addFriend(karol);

        marcin.sayHelloToFriends();

        marcin.processFriends(friends -> {
            friends.clear();
            return friends;
        });

        marcin.sayHelloToFriends();

        marcin.addFriend(donald).addFriend(karol);

        marcin.processFriendsInPlace(List::clear);

        marcin.sayHelloToFriends();

        marcin.addFriend(donald).addFriend(karol);

        marcin.chooseBestFriend(friends -> friends.get(0)).print();

        marcin.processFriendsInPlace(List::clear);
        //error
        marcin.chooseBestFriend(friends -> friends.get(0)).print();

    }
}