---
layout: post
title: "Builders and Factories"
date: 2013-01-09 18:41
comments: true
categories:
- Programming
- Java
---

I started to write a 'quick' response to [this post about builders][OP] and it kind of got out of hand, so I'm putting it up here instead.

The post isn't bad, but I think that Marcin is getting the builder and factory patterns a little mixed up. To recap:

The intent of the **Builder Pattern** is to separate out the construction of an object from it's final representation. Doing this makes it easier to enforce preconditions and invariants, and also makes the object construction easier to read in languages without keyword arguments.

The intent of the **Factory Pattern** on the other hand, is to delegate responsibility for creating an object to somebody else. It is commonly used in dependency injection frameworks.

A concrete example should serve to illustrate the differences.

### A Builder Example

Assume that we have an interface for a simple Pojo:

```java
public interface Employee {
    public Date getHiredAt();
    public String getId();
    public String getName();
    public int getSalary();
    public String getTitle();
}
```

and a default implementation (shown here as a separae class, but it could also be a static inner class in the interface):

```java
class EmployeeImpl implements Employee {

    private final String _id;
    private final String _name;
    private final String _title;
    private final int _salary;
    private final Date _hiredAt;

    EmployeeImpl(String id, String name, String title, int salary, Date hiredAt) {
        _id = id;
        _name = name;
        _title = title;
        _salary = salary;
        _hiredAt = hiredAt;
    }

    public Date getHiredAt() {
        return _hiredAt;
    }

    public String getId() {
        return _id;
    }

    // ...

    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
            .add("id", _id)
            .add("name", _name)
            .add("title", _title)
            .add("salary", _salary)
            .add("hiredAt", _hiredAt)
            .toString();
    }
}
```

Even with just a few fields like this invoking the constructor becomes somewhat ugly:

```java
Employee e = new EmployeeImpl(
    "1", "Fred Foobar", "Engineer", 100000, new Date());
```

Without referring to the docs or source code how do you know what all of those strings mean? How do you know that you have them in the correct order? And if it seems reasonable clear in this example imaging if your Pojo was mainly non-string data!

```java
MyPojo p = new MyPojoImpl(123, true false, false 45.83, "wtf???");
```

Clear as mud, right?

Other languages don't have this problem, in Objective-C we can write

```objc
id obj = [EGEmployee employeeWithId:@"1" name:@"Fred Foobar"
        title:@"Apple Engineer" salary:200000 hiredAt:@"2001-03-24"];
```

which is much clearer. Adding a builder allows us to gain the same level of clarity in Java, and it provides a good place for us to perform any additional checks before creating the object. Here's a typical implementation and an example of calling it:

```java
public class Builder {

    private static final AtomicInteger _ids = new AtomicInteger();

    private static String checkString(String value, String name) {
        value = nullToEmpty(value).trim();
        checkArgument(!value.isEmpty(), "%s cannot be null or empty", name);
        return value;
    }

    private String _id;
    private String _name;
    private String _title;
    private Integer _salary;
    private Date _hiredAt;

    public Builder hiredAt(Date hiredAt) {
        _hiredAt = hiredAt;
        return this;
    }

    public Builder id(String id) {
        _id = id;
        return this;
    }

    public Builder name(String name) {
        _name = name;
        return this;
    }

    public Builder salary(int salary) {
        checkArgument(salary >= 0, "salary cannot be negative");
        _salary = salary;
        return this;
    }

    public Builder title(String title) {
        _title = title;
        return this;
    }

    public Employee build() {
        return new Impl(
                _id != null ? _id : String.format("emp:%06d", _ids.incrementAndGet()),
                checkString(_name, "name"),
                checkString(_title, "title"),
                checkNotNull(_salary, "salary"),
                _hiredAt != null ? _hiredAt : new Date());
    }
}

// elsewhere ...
public Employee findEmployeeById(String id) {
    if ("1".equals(id)) {
        return new Builder().id(id)
            .name("Fred Foobar")
            .title("Engineer")
            .salary(100000)
            .hiredAt("2001-03-24").build();
    }
    return null;
}
```

Again, all pretty clear now, and HotSpot will inline all of those method calls no there should be no additional overhead once the JVM is up and running. 

### A Factory Example

Factories are different, but it would be common for a factory to *use* a builder to create the objects that it vends. For example, here is a factory for employee objects (it doesn't need to have the word Factory in it's name):

```java
public interface Employees {
    Iterable<Employee> all();
    Employee findbyId(String id);
}
```

We can then have different implementations of this, maybe one that loads data from a CSV or JSON file for testing purposes, and one that loads data via JDBC for production use.

**Aside:** if you're familiar with *domain-driven design* you'll be forgiven for noticing a lot of overlap between the factory pattern and DDD's concept of *repositories,* they're very similar concepts. One difference being that factories are often able to create new objects *ex nihilo* while repositories usually retreive objects from external sources. Compare the `findById()` method with the `newInstance()` methods employed by many of the factory classes in the JDK.

Hopefully you can see from tis post that the two patterns have different, if complementary, aims.

A complete example project with all of this code, as well as test cases and a CSV based implementation the the factory are available [on Github][GH].

[OP]: http://toomuchcoding.blogspot.ie/2013/01/hamcrest-matchers-guava-predicate-and.html
[GH]: https://github.com/ianp/builder-example

