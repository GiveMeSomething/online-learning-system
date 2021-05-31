/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.common;

/**
 *
 * @author AS
 */
public class ChangePassService {

    private final ChangePassRepository changePass;

    public ChangePassService() {
        changePass = new ChangePassRepository();
    }

    public void changePassword(String user_email, String password) {
        try {
            changePass.changePassword(user_email, password);
            System.out.println("done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean checkCurPass(String user_email, String curPass) {
        try {
            Boolean check = changePass.checkCurPass(user_email, curPass);
            return check;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
