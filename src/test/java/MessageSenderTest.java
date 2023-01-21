import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.*;
import ru.netology.geo.*;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.*;

import java.util.*;

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
        String testIp = GeoServiceImpl.MOSCOW_IP;
        Country testCountry = Country.RUSSIA;
        Map<String, String> test = new HashMap<>();
        test.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIp);

        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry())
                .thenReturn(testCountry);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(testIp))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(testCountry))
                .thenReturn(expected);

        //act:
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
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
        String testIp = GeoServiceImpl.NEW_YORK_IP;
        Country testCountry = Country.USA;
        Map<String, String> test = new HashMap<>();
        test.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIp);

        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry())
                .thenReturn(testCountry);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(testIp))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(testCountry))
                .thenReturn(expected);

        //act:
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String preferences = messageSender.send(test);

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println();
        System.out.println("send test for other country finished...");
    }


}
