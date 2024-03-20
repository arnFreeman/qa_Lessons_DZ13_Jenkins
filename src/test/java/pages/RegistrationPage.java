package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    final CalendarComponent calendarComponent = new CalendarComponent();
    final CheckResultComponent checkResultComponent = new CheckResultComponent();
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            userGenderInput = $("#genterWrapper"),
            userSubjectsInput = $("#subjectsInput"),
            userHobbiesInput = $("#hobbiesWrapper"),
            upLoadUserPicture = $("#uploadPicture"),
            userAdressInput = $("#currentAddress"),
            userStateInput = $("#state"),
            userCityInput = $("#city"),
            clickButtonSubmit = $("#submit"),
            checkError = $("#app");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public void closeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserGender(String value) {
        userGenderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserDateOfBirth(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setUserSubjects(String value) {
        userSubjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setUserHobbies(String value) {
        userHobbiesInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage upLoadUserPicture(String value) {
        upLoadUserPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setUserAdress(String value) {
        userAdressInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserState(String value) {
        userStateInput.click();
        userStateInput.$(byText(value)).click();
        return this;
    }

    public void setUserCity(String value) {
        userCityInput.click();
        userCityInput.$(byText(value)).click();
    }

    public void clickSubmit() {
        clickButtonSubmit.click();
    }

    public void checkResultNegative() {
        checkError.shouldNotHave((text("Thanks for submitting the form")));
    }

    public RegistrationPage checkResult(String key, String value) {
        checkResultComponent.getResultTable(key, value);
        return this;
    }
}
