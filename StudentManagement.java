import java.util.*;
import java.io.*;

class Student {
    int rollNo;
    String name;
    String course;

    Student(int rollNo, String name, String course) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
    }
}

public class StudentManagement {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        students.add(new Student(roll, name, course));
        System.out.println("Student Added Successfully!");
    }

    public static void displayStudents() {
        if(students.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }

        System.out.println("\nRollNo\tName\tCourse");
        for(Student s : students) {
            System.out.println(s.rollNo + "\t" + s.name + "\t" + s.course);
        }
    }

    public static void searchStudent() {
        System.out.print("Enter Roll No to Search: ");
        int roll = sc.nextInt();

        for(Student s : students) {
            if(s.rollNo == roll) {
                System.out.println("Student Found:");
                System.out.println("RollNo: " + s.rollNo);
                System.out.println("Name: " + s.name);
                System.out.println("Course: " + s.course);
                return;
            }
        }
        System.out.println("Student Not Found.");
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for(Student s : students) {
                fw.write(s.rollNo + "," + s.name + "," + s.course + "\n");
            }
            fw.close();
            System.out.println("Saved to File!");
        } catch(IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static void loadFromFile() {
        students.clear();
        try {
            File file = new File("students.txt");
            if(!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            Scanner fileSc = new Scanner(file);
            while(fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                String[] data = line.split(",");
                students.add(new Student(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2]
                ));
            }
            fileSc.close();
            System.out.println("Loaded from File!");
        } catch(Exception e) {
            System.out.println("Error loading file.");
        }
    }

    public static void main(String[] args) {
        int choice;

        while(true) {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch(choice) {
                case 1: addStudent(); break;
                case 2: displayStudents(); break;
                case 3: searchStudent(); break;
                case 4: saveToFile(); break;
                case 5: loadFromFile(); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid Choice");
            }
        }
    }
}
