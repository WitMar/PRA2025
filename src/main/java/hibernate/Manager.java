package hibernate;

import hibernate.model.Address;
import hibernate.model.Employee;
import hibernate.queries.Queries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;

import java.util.List;
import java.util.Random;


class Manager {

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:hsqldb:mem:PRA", "sa", "")
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();

        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            // FACTORY NAME HAS TO MATCHED THE ONE FROM PERSISTED.XML !!!
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();
            Session session = entityManager.unwrap(Session.class);

            //New transaction
            session.beginTransaction();

            // Create Employee
            Employee emp = createEmployee();

            // Save in First order Cache (not database yet)
            session.persist(emp);

            Employee employee = session.get(Employee.class, emp.getId());
            if (employee == null) {
                System.out.println(emp.getId() + " not found! ");
            } else {
                System.out.println("Found " + employee);
            }

            System.out.println("Employee " + employee.getId() + " " + employee.getFirstName() + employee.getLastName());

            changeFirstGuyToNowak(session);
            employee.setLastName("NowakPRE" + new Random().nextInt()); // No SQL needed
            session.flush();
            session.refresh(employee);
            employee.setLastName("NowakPRE" + new Random().nextInt()); // No SQL needed

            List<Employee> employees = new Queries(session).getThemAll();
            
            //Commit transaction to database
            session.getTransaction().commit();
            session.refresh(employee);
            System.out.println("Done");

            session.close();


            session.beginTransaction();
            Address add = session.get(Address.class, 1);

            // Need to be not null before commmit
            employee.getAddress().setStreet("noname");
            employee.setAddress(add);
            session.persist(employee);
            session.getTransaction().commit();

            session.beginTransaction();
            Address add2 = session.get(Address.class, 1);
            employee.getAddress().setStreet(null);
            session.persist(add2);
            session.getTransaction().commit();

            session.close();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {

        }

    }

    private static Employee createEmployee() {
        Employee emp = new Employee();
        emp.setFirstName("Jan");
        emp.setLastName("Polak" + new Random().nextInt());
        emp.setSalary(100);
        emp.setPesel(new Random().nextInt());
        return emp;
    }

    static void changeFirstGuyToNowak(Session session) {

        List<Employee> employees = new Queries(session).getEmployeeByName("Polak");

        employees.get(0).setLastName("NowakPRE" + new Random().nextInt());

    }

}