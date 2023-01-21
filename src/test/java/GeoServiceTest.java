import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
        GeoService geoServiceTest = Mockito.mock(GeoService.class);
        Location testLocation = Mockito.mock(Location.class);

        if (testIp.startsWith("172.")) {
            Mockito.when(testLocation.getCountry())
                    .thenReturn(Country.RUSSIA);
        } else if (testIp.startsWith("96.")) {
            Mockito.when(testLocation.getCountry())
                    .thenReturn(Country.USA);
        }

        Mockito.when(geoServiceTest.byIp(testIp))
                .thenReturn(testLocation);

        //act:
        String expected = String.valueOf(geoServiceTest.byIp(testIp).getCountry());
        String preferences = String.valueOf(geoServiceImpl.byIp(testIp).getCountry());

        //assert:
        Assertions.assertEquals(expected, preferences);

        System.out.println("byIp test finished...");
    }

}
