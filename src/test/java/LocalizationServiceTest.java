import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {

    @BeforeAll
    static void init(){
        System.out.println("*start LocalizationService tests*");
    }

    @AfterAll
    static void endClass(){
        System.out.println("*finished LocalizationService tests*");
    }

    @BeforeEach
    void start() {
        System.out.println("---------------------------------");
    }

    @AfterEach
    void end() {
        System.out.println("*********************************");
    }

    @Test
    void localeTest() {
        System.out.println("locale test started...");

        //arrange:
        Country countryTest = Country.RUSSIA;
        LocalizationService localizationServiceImpl = new LocalizationServiceImpl();
        LocalizationService localizationServiceTest = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceTest.locale(countryTest))
                .thenReturn("Добро пожаловать");

        //act:
        String expected = localizationServiceTest.locale(countryTest);
        String preferences = localizationServiceImpl.locale(countryTest);

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println("locale test finished...");
    }

}
