import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.*;

import static ru.netology.geo.GeoServiceImpl.*;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderTest {

    @BeforeAll
    static void init(){
        System.out.println("*start MessageSender tests*");
    }

    @AfterAll
    static void endClass(){
        System.out.println("*finished MessageSender tests*");
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
    void sendTestForRussia() {
        System.out.println("send test for Russia started...");

        //arrange:
        String expected = "Добро пожаловать";
        String testIp = MOSCOW_IP;

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(testIp))
                .thenReturn(new Location(testIp, Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> test = new HashMap<String, String>();
        test.put(IP_ADDRESS_HEADER, testIp);

        //act:
        String preferences = messageSender.send(test);

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println();
        System.out.println("send test for Russia finished...");
    }

    @Test
    void sendTestForOtherCountry() {
        System.out.println("send test for other country started...");

        //arrange:
        String expected = "Welcome";
        String testIp = NEW_YORK_IP;

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(testIp))
                .thenReturn(new Location(testIp, Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> test = new HashMap<String, String>();
        test.put(IP_ADDRESS_HEADER, testIp);

        //act:
        String preferences = messageSender.send(test);

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println();
        System.out.println("send test for other country finished...");
    }


}
