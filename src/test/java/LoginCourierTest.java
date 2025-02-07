import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
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
    public static String wrongPassword = "9876";
    public static String wrongLogin = "ninjaj123a";

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Проверка авторизации курьера с набором валидных данных")
    public void loginCourier() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest);

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().statusCode(200);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("id", instanceOf(Integer.class));

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
                .assertThat().statusCode(400);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));


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
                .assertThat().statusCode(400);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));


    }

    @Test
    @DisplayName("Авторизация курьера c несуществующей парой логин-пароль")
    @Description("Проверка невозможности авторизации с несуществующими данными для входа")
    public void loginCourierWithNonExistentCredential () {

        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().statusCode(404);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера c неверным паролем")
    @Description("Проверка невозможности авторизации c неверным паролем")
    public void loginCourierWithWrongPassword () {
        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, wrongPassword);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().statusCode(404);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера c неверным логином")
    @Description("Проверка невозможности авторизации c неверным логином")
    public void loginCourierWithWrongLogin () {
        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(wrongLogin, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().statusCode(404);
        courierSteps.courierLogin(courierLoginRequest)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier() {
        CourierSteps courierSteps = new CourierSteps();
        LoginCourier validCourierLoginRequest = new LoginCourier(login, password);
        courierSteps.courierDeleteAfterLogin(validCourierLoginRequest);
    }

}
