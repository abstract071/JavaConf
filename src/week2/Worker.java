package week2;

public abstract class Worker implements Salary, Comparable<Worker>{
	     
	protected static int counter = 1;
	protected int id;
	protected String name;
	protected double salary;
	
	public Worker (){
		this.name = "";
		this.id = counter++;
	}

	public Worker (String name){
		this.name = new String(name);
		this.id = counter++;
	}
	
	@Override
	public int compareTo(Worker anotherWorker){
		if (this.salary < anotherWorker.salary) return 1;
		if (this.salary > anotherWorker.salary) return -1;
		
		return this.name.compareTo(anotherWorker.getName());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return (id +"\t"+name + "\t" + Math.round(salary * 100.0)/100.0);
	}
}