
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {

    @BeforeAll
    static void init(){
        System.out.println("*start GeoService tests*");
    }

    @AfterAll
    static void endClass(){
        System.out.println("*finished GeoService tests*");
    }

    @BeforeEach
    void start() {
        System.out.println("---------------------------------");
    }

    @AfterEach
    void end() {
        System.out.println("*********************************");
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "96.44.183.149"})
    void byIpTest(String testIp) {
        System.out.println("byIp test started...");

        //arrange:
        GeoService geoServiceImpl = new GeoServiceImpl();
        Country testCountry = null;
        GeoService geoServiceTest = Mockito.mock(GeoService.class);
        if (testIp.startsWith("172.")) {
            testCountry = Country.RUSSIA;
        } else if (testIp.startsWith("96.")) {
            testCountry = Country.USA;
        }
        Mockito.when(geoServiceTest.byIp(testIp))
                .thenReturn(new Location(testIp, testCountry, null, 0));

        //act:
        String expected = String.valueOf(geoServiceImpl.byIp(testIp).getCountry());
        String preferences = String.valueOf(geoServiceTest.byIp(testIp).getCountry());

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println("byIp test finished...");
    }

}
