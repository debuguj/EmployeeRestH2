package pl.debuguj.employee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by grzesiek on 15.09.17.
 */
@Entity
@XmlRootElement
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private BigDecimal salary;
    private int classification;

    public Employee() {
    }

    public Employee(String name, String surname, BigDecimal salary, int classification) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.classification = classification;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!name.equals(employee.name)) return false;
        return surname.equals(employee.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{")
                .append("name='").append(name).append('\'')
                .append(", id='").append(id).append('\'')
                .append(", surname='").append(surname).append('\'')
                .append(", salary='").append(salary).append('\'')
                .append(", classification='").append(classification).append('\'')
                .append('}');
        return sb.toString();
    }
}
