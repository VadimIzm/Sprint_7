import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import ru.project.CourierSteps;
import ru.project.NewCourier;
import ru.project.LoginCourier;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class NewCourierTest {
    public static String login = "naruto";
    public static String password = "1234";
    public static String firstName = "Наруто";


    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверяем, что курьера можно создать с валидными данными")
    public void createNewCourier() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest)
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

        courierSteps.courierDeleteAfterLogin(courierLoginRequest);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Попытка создать двух курьеров с одинаковым набором данных. Создание второго курьера должно провалиться")
    public void createTwoIdenticalCouriers() {

        NewCourier courierCreateRequest = new NewCourier(login, password, firstName);
        LoginCourier courierLoginRequest = new LoginCourier(login, password);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest)
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

        courierSteps.courierCreate(courierCreateRequest)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);

        courierSteps.courierDeleteAfterLogin(courierLoginRequest);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Попытка создать курьера без передачи поля login. Создание курьера должно провалиться")
    public void createCourierWithoutLogin() {
        NewCourier courierCreateRequest = new NewCourier(login, null, firstName);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Попытка создать курьера без передачи поля password. Создание курьера должно провалиться")
    public void createCourierWithoutPassword() {
        NewCourier courierCreateRequest = new NewCourier(null, password, firstName);
        CourierSteps courierSteps = new CourierSteps();

        courierSteps.courierCreate(courierCreateRequest)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

} //конечная
