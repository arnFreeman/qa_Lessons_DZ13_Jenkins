package tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class PracticeFormWithPOForJenkinsTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();


    @Tag("dz13")
    @Test
    @DisplayName("Тест для Practice Form с заполнением всех полей")
    void positiveRegistrationTest() {

        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage()
                    .closeBanner();
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
        step("Проверить данные на соответствие в форме Thanks for submitting the form", () -> {
            registrationPage.checkResult("Student Name", "Renat Taner")
                    .checkResult("Student Email", "renat@taner.com")
                    .checkResult("Gender", "Other")
                    .checkResult("Mobile", "9876543210")
                    .checkResult("Date of Birth", "28 April,1900")
                    .checkResult("Subjects", "Commerce")
                    .checkResult("Hobbies", "Reading")
                    .checkResult("Picture", "1.JPG")
                    .checkResult("Address", "Baikonur Cosmodrome")
                    .checkResult("State and City", "Uttar Pradesh Lucknow");
        });
    }

    @Tag("dz13")
    @Test
    @DisplayName("Негативный тест для Practice Form с заполнением не всех полей")
    void negativeRegistrationTest() {

        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage()
                    .closeBanner();
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

        step("Открыть страницу Practice Form", () -> {
            registrationPage.openPage()
                    .closeBanner();
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
            registrationPage.checkResult("Student Name", "Renat Taner")
                    .checkResult("Gender", "Other")
                    .checkResult("Mobile", "9876543210")
                    .checkResult("Date of Birth", "28 April,1900");
        });
    }
}
