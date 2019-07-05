package net.cetron.base.concurrent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {


    public static void main(String[] args) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        Person p1 = new Person(1, "Gavin", 2);
        atomicIntegerFieldUpdater.addAndGet(p1, 20);
        System.out.println(p1);
        System.out.println(atomicIntegerFieldUpdater.compareAndSet(p1, 51, 32));;
        System.out.println(p1);
        System.out.println(atomicIntegerFieldUpdater.compareAndSet(p1, 22, 32));;
        System.out.println(p1);
    }

    static class Person {
        public volatile int id;
        public volatile String name;
        public volatile int age;

        public Person(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

