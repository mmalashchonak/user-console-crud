import com.innowise.usersapp.utils.Validator;
import org.junit.Assert;

import org.junit.Test;

public class ValidatorTest {

    private static final String TEST_EMAIL_TRUE = "email1@gmail.com";
    private static final String TEST_PHONES_STRING_TRUE = "375253123654, 375657894356";
    private static final String TEST_PHONE_TRUE = "375253123654";
    private static final String TEST_ROLES_STRING_TRUE = "1, 4";

    private static final String TEST_EMAIL_FALSE = "email1@gmailcom";
    private static final String TEST_PHONES_STRING_FALSE = "37525312354, 325657894356";
    private static final String TEST_PHONE_FALSE = "3752531236554";
    private static final String TEST_ROLES_STRING_FALSE = "1. 5";

    @Test
    public void testEmailValidation() {
        boolean actual = Validator.emailValidation(TEST_EMAIL_TRUE);
        Assert.assertTrue(actual);
        boolean actual2 = Validator.emailValidation(TEST_EMAIL_FALSE);
        Assert.assertFalse(actual2);
    }


    @Test
    public void testPhoneValidation() {
        boolean actual = Validator.phoneValidation(TEST_PHONE_TRUE);
        Assert.assertTrue(actual);
        boolean actual2 = Validator.phoneValidation(TEST_PHONE_FALSE);
        Assert.assertFalse(actual2);
    }

    @Test
    public void testPhonesStringValidation() {
        boolean actual = Validator.phonesStringValidation(TEST_PHONES_STRING_TRUE);
        Assert.assertTrue(actual);
        boolean actual2 = Validator.phonesStringValidation(TEST_PHONES_STRING_FALSE);
        Assert.assertFalse(actual2);
    }

    @Test
    public void testRolesStringValidation() {
        boolean actual = Validator.rolesStringValidation(TEST_ROLES_STRING_TRUE);
        Assert.assertTrue(actual);
        boolean actual2 = Validator.rolesStringValidation(TEST_ROLES_STRING_FALSE);
        Assert.assertFalse(actual2);
    }
}