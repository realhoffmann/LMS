package LMS.entities;

public class RegionalDelivery extends Delivery{
    private boolean express; //Flag value for express option. Defaults to false (no express).

    public RegionalDelivery(long id, String from, String to, boolean express){
        super(id, from, to);
        this.express = express;

    }
    public RegionalDelivery(long id, String from, String to){
        super(id, from, to);

    }

    @Override
    public int getTotal() {

        if(express){
            return (int) totalMass() / 100 * 20 + (int) totalMass();
        }else {
            return (int) totalMass();
        }
    }
}
