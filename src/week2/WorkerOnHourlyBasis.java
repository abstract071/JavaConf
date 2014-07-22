package week2;

public class WorkerOnHourlyBasis extends Worker{
	protected double hourCost;
	
	public WorkerOnHourlyBasis() {
		super();
		this.hourCost = 0;
		this.salary = getSalary();
	}

	public  WorkerOnHourlyBasis(String name, double hourCost){
		super(name);
		this.hourCost = hourCost;
		this.salary = getSalary();
	}

	
	public double getHourCost() {
		return hourCost;
	}

	public void setHourCost(double hourCost) {
		this.hourCost = hourCost;
	}

	
	@Override
	public double getSalary(){
		return (20.8 * 8 * hourCost);	
	}
}
