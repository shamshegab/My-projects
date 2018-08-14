
package strategy;


public class NotControlled implements StartegyControlled{

    @Override
    public boolean control() {
        return false;
    }
    
}