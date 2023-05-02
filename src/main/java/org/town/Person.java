package org.town;

import java.util.Objects;

public class Person {
    private final String socialSecurity;
    private final String name;

    public Person(String socialSecurity, String name) {
        this.socialSecurity = socialSecurity;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return socialSecurity.equals(person.socialSecurity) && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurity, name);
    }
}
