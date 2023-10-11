package Tests;



import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args)  {
        System.out.println("Tests");
        Class<?>[] Tests = {RegistrationTest.class,AuthenticationTest.class,DebitTest.class};
        for (Class<?> t : Tests){
            Result result = JUnitCore.runClasses(t);

            if (result.wasSuccessful()) {
                System.out.println("Все тесты "+ t +" пройдены успешно!");
            } else {
                System.out.println("Тесты не пройдены:");
                for (Failure failure : result.getFailures()) {
                    System.out.println(failure.toString());
                }

            }
        }

    }


}
