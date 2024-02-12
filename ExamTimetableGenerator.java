import java.util.*;

public class ExamTimetableGenerator {

    static class Subject {
        String subjectCode;
        String subjectName;
        // Add any other relevant attributes here

        public Subject(String subjectCode, String subjectName) {
            this.subjectCode = subjectCode;
            this.subjectName = subjectName;
        }

        // Add getters and setters if needed
    }

    static class Student {
        String studentID;
        String name;
        List<Integer> availableDays; // List of available days (1-indexed)

        public Student(String studentID, String name, List<Integer> availableDays) {
            this.studentID = studentID;
            this.name = name;
            this.availableDays = availableDays;
        }

        // Add getters and setters if needed
    }

    static class ExamTimetable {
        Subject subject;
        Date date;
        String time;

        public ExamTimetable(Subject subject, Date date, String time) {
            this.subject = subject;
            this.date = date;
            this.time = time;
        }

        // Add getters and setters if needed
    }

    public static void main(String[] args) {
        // Collect information about subjects and students
        List<Subject> subjects = collectSubjects();
        List<Student> students = collectStudents();

        // Generate exam timetable
        List<ExamTimetable> timetable = generateExamTimetable(subjects, students);

        // Print exam timetable
        printExamTimetable(timetable);
    }

    public static List<ExamTimetable> generateExamTimetable(List<Subject> subjects, List<Student> students) {
        List<ExamTimetable> timetable = new ArrayList<>();
        
        int dayIndex = 0; // Index for available days of students
        for (Student student : students) {
            for (Integer day : student.availableDays) {
                // Check if the day index is within the available days of the student
                if (dayIndex >= student.availableDays.size())
                    break;

                // Iterate through subjects and assign them to available slots
                for (Subject subject : subjects) {
                    // Generate exam timetable for the subject on the current day
                    Date date = new Date(2024, 2, day); // Assuming year 2024 and month 2
                    String time = "9:00 AM"; // You can generate time based on your requirements

                    // Update ExamTimetable object with scheduled exam
                    timetable.add(new ExamTimetable(subject, date, time));
                }
                dayIndex++;
            }
        }
        return timetable;
    }

    // Other methods remain unchanged

    public static List<Subject> collectSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects:");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numSubjects; i++) {
            System.out.println("Enter details for Subject " + (i + 1) + ":");
            System.out.print("Subject Code: ");
            String subjectCode = scanner.nextLine();
            System.out.print("Subject Name: ");
            String subjectName = scanner.nextLine();

            Subject subject = new Subject(subjectCode, subjectName);
            subjects.add(subject);
        }

        return subjects;
    }

    public static List<Student> collectStudents() {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for Student " + (i + 1) + ":");
            System.out.print("Student ID: ");
            String studentID = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Available Days (comma-separated list): ");
            String[] availableDaysStr = scanner.nextLine().split(",");
            List<Integer> availableDays = new ArrayList<>();
            for (String dayStr : availableDaysStr) {
                availableDays.add(Integer.parseInt(dayStr.trim()));
            }

            Student student = new Student(studentID, name, availableDays);
            students.add(student);
        }

        return students;
    }

    public static void printExamTimetable(List<ExamTimetable> timetable) {
        System.out.println("Exam Timetable:");
        for (ExamTimetable exam : timetable) {
            System.out.println("Subject: " + exam.subject.subjectName);
            System.out.println("Date: " + exam.date);
            System.out.println("Time: " + exam.time);
            System.out.println();
        }
    }
}
