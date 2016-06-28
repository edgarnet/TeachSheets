import java.util.Scanner;
import java.io.File;
import java.io.IOException;
/**
 * This is the "home" class for TeachSheets.
 * Includes:
 * "Main" method which uses functions from DataProcess class.
 * Scanner class to collect user input.
 * Absolute parameters for 'grid' array.
 * Interactive menu based on a continuous while loop and switch statement.
 * 
 * @author Edgar Perez
 * @version Version 1.0 March 19, 2016
 */
public class TeachSheets
{
    public static void main(String[] args) throws IOException{ //"Main" methods which houses all TeachSheet functionality.
        DataProcess sheets = new DataProcess();  //DataProcess object 'sheets' for DataProcess methods.
        Scanner keyboard = new Scanner(System.in);  //Scanner object 'keyboard' for user input.
        System.out.println("How many students are in your class?");
        int setY = keyboard.nextInt();  //Sets the amount of rows for 'grid' array.
        double[][] grid = new double [10][setY];  //Stable array for all methods.
        sheets.initializeSheet(grid, "OmegaFile.txt");  //Initalizes array data from specified file.
        
        while(true) {  //Prints the menu for every input except when 'Exit' is selected
            sheets.printSheet(grid);  //Aesthetics and formating for 'grid' array.
            System.out.println("Enter (1) Change Grades (2) Weight Grades (3) Homework Average (4) Midterm Average (5) Final Average (6) Exit"); //Menu Screen.
            int input = keyboard.nextInt();  //Determines menu select for switch statement.
            switch(input) {  //Determines which parameters and method to call for DataProcess.
                case 1:
                    System.out.println("Enter Student Number:");
                    int student = keyboard.nextInt();  //The row of 'grid' array.
                    System.out.println("Enter 1-4 for Homework, 5-6 for a Midterm, or 7 for the Final:");
                    int assignment = keyboard.nextInt(); //The column of 'grid' array.
                    System.out.println("Enter Grade Percentage:");
                    double value = keyboard.nextDouble();  //The data replaced in the given cell.
                    sheets.inputData(grid,assignment,student,value);  //Takes it all and replaces it within the 'grid' array.
                    break;
                case 2:
                    System.out.println("Enter Homework Weight as a Decimal:");
                    double firstWeight = keyboard.nextDouble();  //The graded weight of the homework
                    System.out.println("Enter Midterm Weight as a Decimal:");
                    double secondWeight = keyboard.nextDouble();   //The graded weight of the midterms.
                    System.out.println("Enter Final Weight as a Decimal:");
                    double thirdWeight = keyboard.nextDouble();      //The graded weight of the final.
                    sheets.weightedGrade(grid,firstWeight,secondWeight,thirdWeight);  //Takes the weights and determines each student's grade.
                    break;
                case 3:
                    System.out.println("Enter 1-4 for Homework Average:");
                    int homework = keyboard.nextInt();  //The homework assignment selected.
                    sheets.homeworkAverage(grid,homework);  //Determines the average of the selected assignment.
                    break;
                case 4:
                    System.out.println("Enter 1 or 2 for Midterm Average:");
                    int midterm = keyboard.nextInt();  //The midterm exam selected.
                    sheets.midtermAverage(grid,midterm); //Determines the average of the selected exam.
                    break;
                case 5:
                    sheets.finalAverage(grid);  //Determines the average of the final exam.
                    break;
                case 6:
                    sheets.fileWrite(grid, "OmegaFile.txt");  //Saves user's data to the given file.
                    System.out.print("Thank you for using TeachSheets!\nSaving your data...");
                    System.exit(0);  //Closes program.
                default:
                    System.out.println("Please enter another option:");
            } 
        }
    }
}
