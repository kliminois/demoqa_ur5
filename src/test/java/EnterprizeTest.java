import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class EnterprizeTest {
    @BeforeAll
    static void beforeAll() {
        //Configuration.browserSize = "1920x1080";
        //Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 5000; // default 4000
    }

    @Test
    void selenideEnterprizeTest() {
        open("https://github.com");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover(); //наводим курсор на solutions
        $$(".HeaderMenu-dropdown-link").findBy(text("Enterprises")).click(); //нашли список элементов и из списка выбираем enterprises (обязательно по findBy)
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform")); //проверка что открылось нужное
    }
    @Test
    void dragAndDropTest1() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDropTo("#column-b"); //Перетаскивает блок A на блок B
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
    @Test
    void dragAndDropTest2() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        actions() //Создаёт цепочку действий
                .moveToElement($("#column-a")) //Перемещает курсор мыши к элементу A
                .clickAndHold() //Зажимает левую кнопку мыши на элементе A (удерживает его)
                .moveToElement($("#column-b")) //Перемещает курсор (и зажатый элемент A) к элементу B
                .release() //Отпускает кнопку мыши, "бросая" элемент A на элемент B
                .perform(); //Выполняет всю цепочку действий
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

}
