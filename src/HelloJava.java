import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class HelloJava {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				/* sLineElements array holds the strings separately for 
				 * the deduction of the functions and the values 
				 * which will be implementing.*/
				String sLineElements[] = line.split(" ");
				/* These if-else statements controls the first strings
				 * and call the matching methods.*/
				if (sLineElements[0].equals("IntegrateReimann"))
					dIntegrateReimanCalculator(sLineElements);
				else if (sLineElements[0].equals("Arcsinh"))
					System.out.println(sLineElements[0] + " " + sLineElements[1] + " Result: " + maclaurinSeries(Double.parseDouble(sLineElements[1])));
				else if (sLineElements[0].equals("Armstrong")){
					System.out.print(sLineElements[0] + " " + sLineElements[1] + " Result: ");
					int iGivenNum = Integer.parseInt(sLineElements[1]);
					int iMaxValue = (int) Math.pow(10, iGivenNum);
					for (int i = 0; i < iMaxValue; i++){
						armstrongNumbers(i);
				}
			}
			}
			scanner.close();
		}
		catch (FileNotFoundException ex){
			System.out.println("No file Found!");
			return;
		}
	}
	/* func1 and func2 are given functions which we will implement.*/
	public static double func1(double x){
		return Math.pow(x, 2) - x + 3;
	}
	public static double func2(double y){
		return Math.pow(3 * Math.sin(y) - 4, 2);
	}
	public static int factCalculator(int k){
		if (k == 1 || k == 0 )
			return 1;
		else
			return  k * factCalculator(k-1);
	}
	/* dIntegrateReimanCalculator method takes the string array 
	 * which we created when we read the text file. After that
	 * the method parses the strings to integers or doubles
	 * for the calculations. After that 
	 * the method controls first index for the decision
	 * which method will be calling, Func1, Func2 or Func3.
	 * Then, the functions which are called do the implementations
	 * and prints the results.*/
	public static void dIntegrateReimanCalculator(String sArray[]){
		double dFirstValue = Double.parseDouble(sArray[2]);
		double dSecondValue = Double.parseDouble(sArray[3]);
		int iNumberOfPartitions = Integer.parseInt(sArray[4]);
		double finalResult = 0;
		int count = iNumberOfPartitions;
		double dIntervalValue = ((dSecondValue - dFirstValue) / iNumberOfPartitions);
		while (count > 0){
			double dNextValue = dFirstValue + dIntervalValue ;
			double dFuncValue = (dFirstValue + dNextValue) / 2;
			if (sArray[1].equals("Func1")){
				double dFuncResult = func1(dFuncValue);
				double dLastResult = dFuncResult * (dNextValue - dFirstValue);
				finalResult += dLastResult;
			}
			else if (sArray[1].equals("Func2")){
				double dFuncResult = func2(dFuncValue);
				double dLastResult = dFuncResult * (dNextValue - dFirstValue);
				finalResult += dLastResult;
			}
			else if (sArray[1].equals("Func3")){
				double dFuncResult = maclaurinSeries(dFuncValue);
				double dLastResult = dFuncResult * (dNextValue - dFirstValue);
				finalResult += dLastResult;
			}
			dFirstValue += dIntervalValue;
			dNextValue += dIntervalValue;
			count--;
		}
		System.out.println(sArray[0]+ " " + sArray[1] + " " + sArray[2] + " " +  sArray[3] + " " + sArray[4] + " Result: " + finalResult);				
	}
	/* This method, maclaurinSeries calculates the approximate value
	 * of function arcsinh(x). Normally the interval is from zero to eternal value
	 * but we take the interval between zero and thirty for this program.  */
	public static double maclaurinSeries(double dValue){
		double dSum = 0;
		int n = 0;
		while ( n < 30 ){
			double dUpperSide = Math.pow(-1, n) * factCalculator( 2 * n );
			double dLowerSide = ( Math.pow(4,n) * Math.pow(factCalculator(n),2) ) * ( 2 * n + 1 );
			double dResult = (dUpperSide / dLowerSide) * (Math.pow(dValue, (2 * n + 1)));
			dSum += dResult;
			n++;
		}
		return dSum;
	}
	/*This method, armstrongNumbers takes a value 
	 * and checks whether the value is armstrong number
	 * or not. First, the method separates the number's
	 * digits and assign them to an array, then does the
	 * implementations on the array elements. Finally,
	 * controls the number if it's an armstrong number.*/
	public static void armstrongNumbers(int value){
		int armsNum = value;
		int count = 0;
		int total = 0;
		int remainder;
		int[] digits = new int[10];
		while (value != 0){
			remainder =  value % 10;
			digits[count] = remainder;
			value /= 10;
			count++;
		}
		for (int item : digits){
			total += Math.pow(item, count);
		}
		if (total == armsNum){
			System.out.print(total + " ");
		}
	}
}