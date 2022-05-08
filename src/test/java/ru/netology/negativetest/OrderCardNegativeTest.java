package ru.netology.negativetest;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardNegativeTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldInputAllFieldsEmpty() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для " +
                "заполнения"));
    }

    @Test
    public void shouldInputFieldNameEmpty() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для " +
                "заполнения"));
    }

    @Test
    public void shouldInputSurnameEng() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Ivanov Sergey");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные " +
                "неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldInputFieldNumberTelEmpty() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Сергей");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для " +
                "заполнения"));
    }

    @Test
    public void shouldInputInvalidNumberTel() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Сергей");
        $("[data-test-id=phone] input").setValue("+7978");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. " +
                "Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldInputTermsAgreementNoClick() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Сергей");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[type=button]").click();
        $(".input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь " +
                "с условиями обработки и использования моих персональных данных и разрешаю " +
                "сделать запрос в бюро кредитных историй"));
    }
}
