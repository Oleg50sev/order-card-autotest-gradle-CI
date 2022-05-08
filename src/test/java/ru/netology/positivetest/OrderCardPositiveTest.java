package ru.netology.positivetest;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardPositiveTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldInputValidValue() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Сергей");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно " +
                "отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldInputHyphenSurname() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов-Петров Сергей");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно " +
                "отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldInputDoubleSurname() {

        SelenideElement form = $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Петров Сергей");
        $("[data-test-id=phone] input").setValue("+79789991100");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно " +
                "отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
