package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Иван", "Иванов", "Ivan@mail.ru", new Car("Lada", 10)));
      userService.add(new User("Петр", "Петров", "Petr@mail.ru", new Car("Niva", 20)));
      userService.add(new User("Игорь", "Сомов", "Igor@mail.ru", new Car("Opel", 30)));
      userService.add(new User("Глеб", "Громов", "Gleb@mail.ru", new Car("Oda", 40)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      String carModel = "Opel";
      int carSeries = 30;
      List<User> user = userService.userPerCar(carModel, carSeries);
      System.out.println("Владельцем машины модели " + carModel + " серии " + carSeries + " является:");
      for (User us : user) {
         System.out.println(us.getFirstName());
         System.out.println(us.getLastName());
         System.out.println(us.getEmail());
         System.out.println();
      }
      context.close();
   }
}
