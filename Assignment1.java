// Author: Parth Patel
// Course: COP3503 / CS2
// Semester: Fall 2025

import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Assignment1
{
    public static int calculatePopularPairs(Scanner scanner)
    {
        // Organizes students by course
        Map<String, Set<String>> courseToStudents = new HashMap<>();

        // Tracks unique student pairs
        Set<String> popularPairs = new HashSet<>();

        // Reads total student count
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        // Processes records for each student
        for (int i = 0; i < numStudents; i++)
        {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String studentName = parts[0]; // First token is the student's name

            // Adda a student to their courses
            for (int j = 1; j < parts.length; j++)
            {
                String courseName = parts[j];
                // Gets the set for this course, or creates a new one if it doesn't exist
                Set<String> studentsInCourse = courseToStudents.getOrDefault(courseName, new HashSet<>());
                studentsInCourse.add(studentName);
                courseToStudents.put(courseName, studentsInCourse);
            }
        }

        // Find pairs within each course
        for (Set<String> studentsInCourse : courseToStudents.values())
        {
            // Only create pairs if more than one student exists
            if (studentsInCourse.size() > 1)
            {
                String[] studentArray = studentsInCourse.toArray(new String[0]); // Converts the set to an array

                // Loop through all possible combinations
                for (int i = 0; i < studentArray.length; i++)
                {
                    for (int j = i + 1; j < studentArray.length; j++)
                    {
                        String student1 = studentArray[i];
                        String student2 = studentArray[j];

                        // Creates  a unique key for the pair
                        String pairKey;
                        // Sorts names by alphabet
                        if (student1.compareTo(student2) < 0)
                        {
                            pairKey = student1 + "-" + student2;
                        }
                        else
                        {
                            pairKey = student2 + "-" + student1;
                        }

                        popularPairs.add(pairKey); // Add to set
                    }
                }
            }
        }

        return popularPairs.size(); // Return total number of unique pairs
    }

    public static void main(String[] args)
    {
        // Read input
        Scanner scanner = new Scanner(System.in);

        int result = calculatePopularPairs(scanner); // Compute result

        // Print the final count
        System.out.println(result);

        scanner.close(); // Close scanner
    }
}
