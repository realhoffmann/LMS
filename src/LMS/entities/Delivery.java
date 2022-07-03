package LMS.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import LMS.provided.*;

public abstract class Delivery implements Comparable<Delivery>
{
	private long id; //Unique id of this delivery
	private String from; //The pick up location where the goods of this delivery are sent from
	private String to; //The drop off location where the goods of this delivery are sent to
	private DateTime collected; //The date and time when this delivery is collected from the sender. A null value means this delivery has not been collected yet
	private DateTime delivered; //The date and time when this delivery is delivered to the recipient. A null value means this delivery has not been delivered yet
	private Vehicle carrier; //The carrier assigned to this delivery. A null value means no carrier has been assigned yet
	private java.util.Set<Item> goods; //The set of goods this delivery transports

	public Delivery(long id, String from, String to){
		if(id > 0){
			this.id = id;
		}else {
			throw new IllegalArgumentException();
		}

		this.from = ensureNonNullNonEmpty(from);
		this.to = ensureNonNullNonEmpty(to);

	}
	public abstract int getTotal();

	protected final float totalMass(){
		float combined = 0;
		if(goods == null){
			return -1;
		}
		else{
			for(Item g : goods){
				combined += g.totalMass();
			}
		}
		return combined;
	}


	public boolean isCollected(){
		if(collected == null){
			return false;
		}
			return true;

	}

	public boolean isDelivered(){
		if(delivered == null) {
			return false;
		}
			return true;
	}

	private boolean isAssigned(){
		if(carrier == null){
			return false;
		}
		return true;
	}

	public final boolean addGoods(Item item){
		if(!isAssigned() && !isDelivered() && !isCollected()){
			goods.add(item);
			return false;
		}
		return false;
	}
	public final boolean addGoods(Iterable<Item> items){

		if(!isAssigned() && !isDelivered() && !isCollected() && goods != null){
			for(Item i : items){
				if(!goods.contains(i)){
					goods.add(i);
					return true;
				}
			}
		}
		return false;
	}
	public java.util.Set<Item> getGoods(){
		Set<Item> copy = new HashSet<>();
		if(goods != null) {
			for (Item i : goods) {
				copy.add(i);
			}
		}

		return copy;
	}
	public final void assignCarrier(Vehicle v){
		if(v.getMaxLoad() >= totalMass() && v != null){
			this.carrier = v;
		}

	}
	public final boolean collect(DateTime toc){
		if(!isCollected() && carrier != null && goods != null){
			collected = new DateTime(toc);
			return true;
		}
		return false;
	}
	public final boolean deliver(DateTime tod){
		if(isCollected() && !isDelivered()){
			delivered = new DateTime(tod);
			return true;
		}
		return false;
	}
	private final String ensureNonNullNonEmpty(String str){
		if(str == null || str.isEmpty()){
			throw new IllegalArgumentException();
		}
		return str;
	}

	public int compareTo(Delivery arg0){
		return Long.compare(id, arg0.id);
	}

	@Override
	public String toString() {
		return String.format(
				"%d from \"%10.10s\" to \"%10.10s\" " + "[%scollected, %sdelivered] (%.2fkg, EUR %.2f)\n" + "%s", id,
				from, to, isCollected() ? "" : "not ", isDelivered() ? "" : "not ", totalMass(), getTotal() / 100.,
				goods == null ? "no stock" : goods);
	}

}
