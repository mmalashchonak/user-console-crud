import com.innowise.usersapp.entity.Roles;
import com.innowise.usersapp.utils.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InputParserTest {

    private static final String TEST_PHONES_STRING_TRUE = "375253123654, 375657894356";
    private static final String TEST_ROLES_STRING_TRUE = "1, 4";

    private final List<Roles> roles = new ArrayList<>();
    private final List<String> phones = new ArrayList<>();

    @Before
    public void fillArrays() {
        roles.add(Roles.USER);
        roles.add(Roles.PROVIDER);

        phones.add("375253123654");
        phones.add("375657894356");
    }

    @Test
    public void testPhonesParser() {
        List<String> actual = InputParser.parsePhones(TEST_PHONES_STRING_TRUE);
        Assert.assertEquals(actual, phones);
    }

    @Test
    public void testRolesParser() {
        List<Roles> actual = InputParser.parseRoles(TEST_ROLES_STRING_TRUE);
        Assert.assertEquals(actual, roles);
    }
}
