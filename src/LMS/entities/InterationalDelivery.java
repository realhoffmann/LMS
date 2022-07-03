package LMS.entities;

import com.sun.jdi.IntegerValue;

public class InterationalDelivery extends Delivery{
    private float custom; //The custom factor. Defaults to 1
    public InterationalDelivery(long id, String from, String to){
       super(id, from, to);
    }
    public InterationalDelivery(long id, String from, String to, float custom){
        super(id, from, to);
        if(custom >= 1){
            this.custom = custom;
        }

    }

    @Override
    public int getTotal() {
        if(getGoods().size() == 0){
            return -1;
        }
        int total = 0;
        for(Item i : getGoods()){
            total += i.totalValue();
        }

        return total * (int) custom;
    }


}
