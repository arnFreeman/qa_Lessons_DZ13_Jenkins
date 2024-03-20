package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class PracticeFormWithPOForJenkinsTest {
    final RegistrationPage registrationPage = new RegistrationPage();
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Последний скриншот");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Tag("dz13")
    @Test
    @DisplayName("Тест для Practice Form с заполнением всех полей")
    void positiveRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage();
        });
        step("Ввести данные в поля Student Registration Form", () -> {
            registrationPage.setFirstName("Renat")
                    .setLastName("Taner")
                    .setUserEmail("renat@taner.com")
                    .setUserGender("Other")
                    .setUserNumber("9876543210")
                    .setUserDateOfBirth("28", "April", "1900")
                    .setUserSubjects("Commerce")
                    .setUserHobbies("Reading")
                    .upLoadUserPicture("1.JPG")
                    .setUserAdress("Baikonur Cosmodrome")
                    .setUserState("Uttar Pradesh")
                    .setUserCity("Lucknow");
        });
        step("Отправить данные для проверки нажав Submit", () -> {
            registrationPage.clickSubmit();
        });
        step("Проверить данные на соответствие в форме Thanks for submitting the form", () ->
                registrationPage.checkResultComponent("Renat Taner", "renat@taner.com", "Other",
                        "9876543210", "28 April,1900", "Commerce", "Reading",
                        "1.JPG", "Baikonur Cosmodrome", "Uttar Pradesh Lucknow"));
    }

    @Tag("dz13")
    @Test
    @DisplayName("Негативный тест для Practice Form с заполнением не всех полей")
    void negativeRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage();
        });
        step("Ввести данные в поля Student Registration Form кроме поля First Name", () -> {
            registrationPage.setLastName("Taner")
                    .setUserEmail("renat@taner.com")
                    .setUserGender("Other")
                    .setUserNumber("9876543210")
                    .setUserDateOfBirth("28", "April", "1900")
                    .setUserSubjects("Commerce")
                    .setUserHobbies("Reading")
                    .upLoadUserPicture("1.JPG")
                    .setUserAdress("Baikonur Cosmodrome")
                    .setUserState("Uttar Pradesh")
                    .setUserCity("Lucknow");
        });
        step("Отправить данные для проверки нажав Submit", () -> {
            registrationPage.clickSubmit();
        });
        step("Проверить отсутствие формы Thanks for submitting the form", () -> {
            registrationPage.checkResultNegative();
        });
    }

    @Tag("dz13")
    @Test
    @DisplayName("Тест для Practice Form с заполнением обязательных полей")
    void requiredFieldsRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage();
        });
        step("Ввести данные в поля Student Registration Form", () -> {
            registrationPage.setFirstName("Renat")
                    .setLastName("Taner")
                    .setUserGender("Other")
                    .setUserNumber("9876543210")
                    .setUserDateOfBirth("28", "April", "1900");
        });
        step("Отправить данные для проверки нажав Submit", () -> {
            registrationPage.clickSubmit();
        });
        step("Проверить данные на соответствие", () -> {
            registrationPage.checkOneResult("Student Name", "Renat Taner")
                    .checkOneResult("Gender", "Other")
                    .checkOneResult("Mobile", "9876543210")
                    .checkOneResult("Date of Birth", "28 April,1900");
        });
    }
}
