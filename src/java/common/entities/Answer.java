/**
 * Jun 17, 2021
 *
 * @author Nguyen Khanh Toan
 */

package common.entities;


public class Answer {
    private String name;

    public Answer() {
    }

    public Answer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Answer{" + "name=" + name + '}';
    }
    
    
    
    
    
}