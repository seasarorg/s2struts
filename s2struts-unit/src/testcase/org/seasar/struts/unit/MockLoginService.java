package org.seasar.struts.unit;

/**
 * @author Satoshi Kimura
 */
public class MockLoginService implements LoginService {

    public MockLoginService() {
    }

    /**
     * @see org.seasar.struts.unit.LoginService#login(java.lang.String, java.lang.String)
     */
    public void login(String id, String pass) {
        if (("id".equals(id) && "pass".equals(pass)) == false) {
            throw new RuntimeException();
        }
    }
}