package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            userGenderInput = $("#genterWrapper"),
            userDateOfBirthInput = $("#dateOfBirthInput"),
            userSubjectsInput = $("#subjectsInput"),
            userHobbiesInput = $("#hobbiesWrapper"),
            upLoadUserPicture = $("#uploadPicture"),
            userAdressInput = $("#currentAddress"),
            userStateInput = $("#state"),
            userCityInput = $("#city"),
            clickButtonSubmit = $("#submit"),
            checkError = $("#app"),
            checkOneResult = $(".table-responsive");
    final CalendarComponent calendarComponent = new CalendarComponent();
    final CheckResultComponent checkResultComponent = new CheckResultComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
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
        userDateOfBirthInput.click();
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
        userAdressInput.setValue(value).pressEnter();
        return this;
    }
    public RegistrationPage setUserState(String value) {
        userStateInput.click();
        userStateInput.$(byText(value)).click();
        return this;
    }
    public RegistrationPage setUserCity(String value) {
        userCityInput.click();
        userCityInput.$(byText(value)).click();
        return this;
    }
    public RegistrationPage clickSubmit() {
        clickButtonSubmit.click();
        return this;
    }
    public RegistrationPage checkResultNegative() {
        checkError.shouldNotHave((text("Thanks for submitting the form")));
        return this;
    }
    public RegistrationPage checkOneResult(String key, String value) {
        checkOneResult.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
    public void checkResultComponent(String studentName, String studentEmail, String gender, String mobile,
                                     String dateOfBirth, String subjects, String hobbies, String picture,
                                     String address, String stateAndCity) {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        checkResultComponent.getResultTable(studentName, studentEmail, gender, mobile, dateOfBirth, subjects,
                hobbies, picture, address, stateAndCity);
    }
}
