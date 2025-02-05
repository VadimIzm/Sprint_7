import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.project.CourierSteps;
import ru.project.NewCourier;
import ru.project.LoginCourier;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

public class LoginCourierTest {

    public static String login = "n1nja";
    public static String password = "1234";
    public static String firstName = "saske";

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Проверка авторизации курьера с набором валидных данных")
    public void loginCourier() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest);

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("id", instanceOf(Integer.class))
                .and()
                .statusCode(200);

        courierSteps.courierDeleteAfterLogin(courierLoginRequest);

    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Проверка невозможности авторизации без передачи поля login")
    public void loginCourierWithoutLogin() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(null, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest);

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);

        LoginCourier validCourierLoginRequest = new LoginCourier(login, password);
        courierSteps.courierDeleteAfterLogin(validCourierLoginRequest);

    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Проверка невозможности авторизации без передачи поля password")
    public void loginCourierWithoutPassword() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, null);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest);

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);

        LoginCourier validCourierLoginRequest = new LoginCourier(login, password);
        courierSteps.courierDeleteAfterLogin(validCourierLoginRequest);

    }

    @Test
    @DisplayName("Авторизация курьера c несуществующей парой логин-пароль")
    @Description("Проверка невозможности авторизации с несуществующими данными для входа")
    public void loginCourierWithNonExistentCredential () {

        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);

    }


}
