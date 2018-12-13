/*
* HW - Bean
* @author Chieh Shih
 * CPE-552
 * Chieh Shih
 * 10431509
 *Ge Chang
 * 10410233
*/
package session13;


public class HWBean extends Object {
    private String firstName, lastName;
    public HWBean(){};
    
    public void setFirstName(String Name){Name = firstName;};
    public String getFirstName(){return firstName;};
    
    public void setLastName(String Name){Name = lastName;};
    public String getLastName(){return lastName;};
    
    public String toString(){
    return firstName + " " + lastName;
    }
}
