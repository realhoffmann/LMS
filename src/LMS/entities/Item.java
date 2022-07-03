package LMS.entities;


public class Item {
	private String description; //The common description of the single items in this shipping item.
	private int amount; //The number of single items in this shipping item. Defaults to 1.
	private float mass; //The mass of a single item in this shipping item in kg.
	private long value; //	private long value;

	public Item(String description, int amount, float mass, long value){
			if(description == null || description.isEmpty()){
				throw new IllegalArgumentException();
			}
			else {
			this.description = description;
		}
			if(amount > 0){
				this.amount = amount;
			}
			if(mass > 0){
				this.amount = amount;
			}
			if(value > 0){
				this.value = value;
			}

	}

	public float totalValue(){
		return value * amount;
	}

	public float totalMass(){
		return mass * amount;
	}


	@Override
	public String toString() {
		return String.format("%d x %.15s (%.1fkg EUR %d.%02d, %.1fkg EUR %d.%02d)", amount, description, mass,
				((int) value) / 100, ((int) value) % 100, totalMass(), ((int) totalValue()) / 100,
				((int) totalValue()) % 100);
	}

}
