package pl.debuguj.employee.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.debuguj.employee.controllers.EmployeeController;
import pl.debuguj.employee.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by grzesiek on 15.09.17.
 */
@Repository
public class EmployeeDaoH2Impl implements EmployeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoH2Impl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee getEmployeeById(int id) {
        try
        {
            return (Employee)em.createQuery("select employee from Employee as employee where employee.id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            logger.info("NoResultException");
            throw e;
        }
    }

    @Override
    public void saveEmployee(Employee e) {
        em.persist(e);
        Employee e1 = (Employee)em.createQuery("select employee from Employee as employee where employee.id=:id")
                .setParameter("id", e.getId())
                .getSingleResult();
    }

    @Override
    public List<Employee> getAllEmployees() {

        return em.createQuery("select employee from Employee as employee").getResultList();
    }

    @Override
    public boolean removeEmployeeById(int id) {


            em.createQuery("delete from Employee where id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;

    }
}
