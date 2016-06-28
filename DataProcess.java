import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * These are the 'sheets' methods from TeachSheets "home" class.
 * Includes:
 * Initilize and Print methods for the 'grid' array called in the "home" class.
 * Input method to change student's grade.
 * Method to produce weighted grade for each student.
 * Methods to produce average for homework assignments, midterm exams, and the final.
 * Write method to save data from 'grid' array to file.
 * 
 * @author Edgar Perez
 * @version Version 1.0 March 19, 2016
 */
public class DataProcess
{
    
    public void initializeSheet(double[][] grid, String text) throws IOException{ //Initializes array data from a file by the given string.
        Scanner fileData = new Scanner(new File(text));  //Scanner object 'fileData' to collect data from the file.
        for(int y = 0; y < grid[0].length; y++){
            for(int x = 0; x < grid.length; x++){
                grid[x][y] = fileData.nextDouble();  //All array parameters are set to the following double within the file.
            }
        }
    }
    
    public void printSheet(double[][] grid){ //Aesthetics for the array.
        System.out.println();
        System.out.println("  Course   Student#     HW1       HW2       HW3       HW4    Midterm1  Midterm2   Finals    Grades"); //Column names.
        System.out.println(" --------  --------  --------  --------  --------  --------  --------  --------  --------  --------");
        for(int y = 0; y < grid[0].length; y++) {
            for(int x = 0; x < grid.length; x++) {
                System.out.printf(" |%6.2f| ", grid[x][y]);  //Formats data in the hundreds-to-hundredths for each cell.
            }
            System.out.println();
        }
        System.out.println(" --------  --------  --------  --------  --------  --------  --------  --------  --------  --------");
    }
    
    public void inputData(double[][] grid, int x, int y, double value){ //Changes a student's specified grade.
        if (x >= 1 && x <= 7 && y > 0 && y <= grid[0].length) {
            x += 1; //Allocates 'x' value accordingly.
            y -= 1; //Allocates 'y' value accordingly.
            grid[x][y] = value;  //Sets the given cell to the specified 'value' or grade. 
        }
        else {System.out.println("That grade does not exist!");}  //Prevents invalid grade alteration.
    }
    
    public void weightedGrade(double[][] grid, double firstWeight, double secondWeight, double thirdWeight){
        double sumHomework = 0;
        double sumMidterm = 0;
        double sumFinal = 0;
        int countHW = 0;
        int countMid = 0;
        double grade = 0;
        double weightTotal = firstWeight + secondWeight + thirdWeight;
        if(weightTotal == 1){
            for(int y = 0; y < grid[0].length; y++) {
                for(int x = 0; x < 4; x++){
                    sumHomework = sumHomework + grid[x+2][y];  //Determines the sum of each student's homework grades.
                    countHW++;  //Collects the amount of homework assignments for each student.
                }
                for(int x = 0; x < 2; x++){
                    sumMidterm = sumMidterm + grid[x+6][y];  //Determines the sum of each student's midterm grades.
                    countMid++;  //Collects the amount of midterm exams for each student.
                }
                sumFinal = grid[8][y];  //Collects the final grade for each student.
                grade = (((sumHomework/countHW)*firstWeight)+((sumMidterm/countMid)*secondWeight)+(sumFinal*thirdWeight));  //Determines the weighted grade for each student.
                grid[9][y] = grade;  //Sets each grade to its respective cell. 
            }
        }
        else{System.out.println("These weights are invalid!");}
    }
    
    public void homeworkAverage(double[][] grid, int x){ //Determines the average of a given homework assignment. 
        double sumAssignment = 0;
        int count = 0;
        double average;
        if(x >= 1 && x <= 4){
            for(int y = 0; y < grid[0].length; y++){
                sumAssignment = sumAssignment + grid[x+1][y];  //Determines the sum of all grades for given homework assignment.
                count++;  //Collects the amount of grades used in sum.
            }
            average = sumAssignment / count;  //Determines the average of an assignment's grades.
            System.out.printf("The Average Grade for Homework %d was: %.2f\n", x, average);  //Outputs formatted result.
        }
        else{System.out.println("That assignment is unavailable.");}  //Prevents invalid averaging.
    }
    
    public void midtermAverage(double[][] grid, int x){ //Determines the average of a given midterm exam.
        double sumMidterm = 0;
        int count = 0;
        double average;
        if(x >= 1 && x <= 2){
            for(int y = 0; y < grid[0].length; y++){
                sumMidterm = sumMidterm + grid[x+5][y];  //Determines the sum of all grades for given midterm exam.
                count++;  //Collects the amount of grades used in sum.
            }
            average = sumMidterm / count;  //Determines the average of a midterm's grades.
            System.out.printf("The Average Grade for Midterm %d was: %.2f\n", x, average);  //Outputs formatted result.
        }
        else{System.out.println("That assignment is unavailable.");}  //Prevents invalid averaging.
    }
    
    public void finalAverage(double[][] grid){ //Determines the average of the final exam.
        double sumFinal = 0;
        int count = 0;
        double average;
        for(int y = 0; y < grid[0].length; y++){
            sumFinal = sumFinal + grid[8][y];  //Determines the sum of all grades for the final exam.
            count++;  //Collects the amount of grades used in sum.
        }
        average = sumFinal / count;  //Determines the average of the final's grades.
        System.out.printf("The Average Grade for the Final was: %.2f\n", average);  //Outputs formatted result.
    }
    
    public void fileWrite(double[][] grid, String text) throws IOException{ //Saves current data to a file from the given string.
        PrintWriter output = new PrintWriter(text);  //PrintWriter object 'output' to print data to the file.
        for(int y = 0; y < grid[0].length; y++){
             for(int x = 0; x < grid.length; x++){
                 output.printf("%.2f ", grid[x][y]);  //Prints and formats every value from 'grid' array into the file.
             }
             output.println();
        }
        if(output!=null){
            output.close();  //Closes PrintWriter 'output'.
        }
    }
}
