package week2;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int menuItem = 0;
		Scanner in = new Scanner(System.in);
		ArrayList<Worker> workers = new ArrayList<>();
		SalaryAndNameComparator comparator = new SalaryAndNameComparator();

		initList(workers);
		workers.sort(comparator);

		System.out.println("Hello! :)");
		System.out
				.println("Here you can see the sorted list of all our workers:");
		printList(workers);
		System.out.println("What do you want to do with it?");
		printMenu();

		while ((menuItem = getMenuNumb(in)) != 6) {
			switch (menuItem) {
			case 1: {
				printList(workers);
				break;
			}
			case 2: {
				printTopFive(workers);
				break;
			}
			case 3: {
				printBottomThree(workers);
				break;
			}
			case 4: {
				saveListToFile(workers);
				break;
			}
			case 5: {
				workers = loadListFromFile(workers);
				workers.sort(comparator);
				break;
			}
			default:{
				System.out.println("You wrote something strange... Try one more time.");
			}
			}

			System.out.println("Anything else?");
			printMenu();
		}

		System.out.println("Bye-bye!");

	}

	public static void saveListToFile(ArrayList<Worker> workers) {
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream("src\\week2\\workersSAVE");
		} catch (FileNotFoundException e) {
			System.err.println("Oops... I can't find the file =(");
			return;
		}

		try (BufferedWriter outF = new BufferedWriter(new OutputStreamWriter(
				fileOutputStream, "UTF-8"))) {
			String workerToString = "";
			for (int i = 0; i < workers.size(); i++) {
				if (workers.get(i).getClass().getName()
						.contains("WorkerWithFixedPayment")) {
					workerToString += "2 ";
					workerToString += workers.get(i).getName() + " "
							+ workers.get(i).getSalary();
				} else {
					workerToString += "1 ";
					workerToString += workers.get(i).getName() + " ";
					workerToString += workers.get(i).getSalary() / 8.0 / 20.8;
				}
				workerToString += "\r\n";
			}
			outF.write(workerToString);
		} catch (UnsupportedEncodingException e) {
			System.err.println("Oops... I can't encode the text in file =(");
			return;
		} catch (IOException e) {
			System.err.println("Oops... I can't close the file =(");
			return;
		}
	}

	public static ArrayList<Worker> loadListFromFile(ArrayList<Worker> workers) {
		ArrayList<Worker> workersTemp = new ArrayList<>();

		try (BufferedReader inF = new BufferedReader(new FileReader(
				"src\\week2\\workersLOAD"))) {
			String inputWorker;
			String[] splitedString;
			while ((inputWorker = inF.readLine()) != null) {
				splitedString = inputWorker.split(" ");

				if (Integer.parseInt(splitedString[0]) == 1) {
					workersTemp.add(new WorkerOnHourlyBasis(splitedString[1],
							Double.parseDouble(splitedString[2])));
				} else if (Integer.parseInt(splitedString[0]) == 2) {
					workersTemp.add(new WorkerWithFixedPayment(
							splitedString[1], Double
									.parseDouble(splitedString[2])));
				} else {
					System.err
							.println("Oops... There are some problems with readding workers type =(");
					return workers;
				}
			}
		} catch (NumberFormatException e) {
			System.err
					.println("Oops... I can't convert the information from file to number =(");
			return workers;
		} catch (IOException e) {
			System.err.println("Oops... I can't close the file =(");
			return workers;
		}

		return workersTemp;
	}

	public static void printBottomThree(ArrayList<Worker> workers) {
		int counter = 3;
		if (workers.size() < 1) {
			return;
		}

		if ((workers.size() < 3) && (workers.size() > 0)) {
			counter = workers.size();
		}

		System.out.println("ID\tNAME\tSALARY");
		for (int i = workers.size() - counter; i < workers.size(); i++) {
			System.out.println(workers.get(i));
		}

		System.out.println();
	}

	public static void printTopFive(ArrayList<Worker> workers) {
		int counter = 5;
		if (workers.size() < 1) {
			return;
		}

		if ((workers.size() < 5) && (workers.size() > 0)) {
			counter = workers.size();
		}

		System.out.println("ID\tNAME\tSALARY");
		for (int i = 0; i < counter; i++) {
			System.out.println(workers.get(i));
		}

		System.out.println();
	}

	public static int getMenuNumb(Scanner in) {
		String line;

		while (true) {
			line = in.nextLine();
			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.err
						.println("Oops... You had to enter some number =( Please, try again: ");
			}
		}
	}

	public static void initList(ArrayList<Worker> workers) {
		workers.add(new WorkerOnHourlyBasis("Anna", 10));
		workers.add(new WorkerOnHourlyBasis("Mary", 10));
		workers.add(new WorkerWithFixedPayment("Damon", 156.0));
		workers.add(new WorkerOnHourlyBasis("Greg", 25));
		workers.add(new WorkerWithFixedPayment("Sally", 5000));
		workers.add(new WorkerWithFixedPayment("Julia", 4256));
		workers.add(new WorkerOnHourlyBasis("Arthur", 12));
		workers.add(new WorkerOnHourlyBasis("Gazhil", 8));
		workers.add(new WorkerWithFixedPayment("Ryan", 3500));
	}

	public static void printList(ArrayList<Worker> workers) {
		System.out.println("ID\tNAME\tSALARY");

		for (Worker w : workers) {
			System.out.println(w);
		}

		System.out.println();
	}

	public static void printMenu() {
		System.out.println("1) Show me all list one more time.");
		System.out.println("2) Show me the Top-5 workers.");
		System.out.println("3) Show me the Bottom-3 workers.");
		System.out.println("4) Save all list to the file.");
		System.out.println("5) Load another list from the file.");
		System.out
				.println("6) A don't want to do anything. Just close this annoying program!");
		System.out.println("Please, write the munber of menu item: ");
	}
}
