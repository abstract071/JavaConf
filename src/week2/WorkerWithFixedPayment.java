package week2;

public class WorkerWithFixedPayment extends Worker{
	protected double fixedMounthPay;
	
	public WorkerWithFixedPayment(){
		super();
		this.fixedMounthPay = 0;
		this.salary = getSalary();
	}
	
	public WorkerWithFixedPayment(String name, double fixedMounthPay){
		super(name);
		this.fixedMounthPay = fixedMounthPay;
		this.salary = getSalary();
	}	
	
	public double getFixedMounthPay() {
		return fixedMounthPay;
	}

	public void setFixedMounthPay(double fixedMounthPay) {
		this.fixedMounthPay = fixedMounthPay;
	}
	
	@Override
	public double getSalary(){
		return fixedMounthPay;	
	}
}
