package virtualclassroom;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver= new ProgramDriver();
        String adminName="admin";
        String adminPassword="admin123";
        Admin admin=new Admin();
        admin.setName(adminName);
        admin.setPassword(adminPassword);
        int adminId=driver.addAdminAccount(admin);
        System.out.println("Admin id is "+adminId);
        while(true){
            System.out.println("1.StudentLogin\n2.Faculty Login\n3.Admin Login");
            int decision=scan.nextInt();
            if(decision==1){
                System.out.println("1.New Account\n2.Existing Account\n3.Check Status");
                int decision1=scan.nextInt();
                scan.nextLine();
                if(decision1==1){
                    System.out.println("Name");
                    String name=scan.nextLine();
                    System.out.println("password");
                    String password=scan.nextLine();
                    System.out.println("gender");
                    String gender=scan.nextLine();
                    System.out.println("Age ");
                    int age=scan.nextInt();
                    System.out.println("Mobile Number");
                    long mobile=scan.nextInt();
                    System.out.println("standard");
                    int standard=scan.nextInt();
                    scan.nextLine();
                    Student student = getStudent(name, password, gender, age, mobile, standard);
                    int result=driver.addStudentAccount(student);
                    System.out.println("Student id is "+result);
                }else if(decision1==2){
                    System.out.println("Student Id");
                    int id=scan.nextInt();
                    scan.nextLine();
                    System.out.println("password");
                    String password=scan.nextLine();
                    if(driver.checkStudentId(id,password)){
                        studentLogin(scan, driver, id);
                    }else {
                        System.out.println("password or id is wrong");
                    }
                }else if(decision1==3){
                    System.out.println("Student Id");
                    int id=scan.nextInt();
                    scan.nextLine();
                    if(driver.checkStudentId(id)){
                        String result=driver.checkStudentStatus(id);
                        System.out.println(result);
                    }else {
                        System.out.println("id is wrong");
                    }

                }
            }else if(decision==2){
                System.out.println("1.New Account\n2.Existing Account");
                int decision1=scan.nextInt();
                scan.nextLine();
                if(decision1==1) {
                    System.out.println("Name");
                    String name = scan.nextLine();
                    System.out.println("password");
                    String password = scan.nextLine();
                    System.out.println("gender");
                    String gender = scan.nextLine();
                    System.out.println("Age ");
                    int age = scan.nextInt();
                    System.out.println("Mobile Number");
                    long mobile = scan.nextInt();
                    scan.nextLine();
                    System.out.println("subject");
                    String subject = scan.nextLine();
                    Faculty faculty = getFaculty(name, password, gender, age, mobile, subject);
                    int result=driver.addFacultyAccount(faculty);
                    System.out.println("Faculty Id is "+result);
                }else if(decision1==2){
                    System.out.println("Faculty Id");
                    int id=scan.nextInt();
                    scan.nextLine();
                    System.out.println("password");
                    String password=scan.nextLine();
                    if(driver.checkFacultyId(id,password)) {
                        facultyLogin(scan, driver, id);
                    }else {
                        System.out.println("password or id is wrong");
                    }
                }else if(decision1==3){
                    System.out.println("Faculty Id");
                    int id=scan.nextInt();
                    scan.nextLine();
                    if(driver.checkFacultyId(id)){
                        String result=driver.checkFacultyStatus(id);
                        System.out.println(result);
                    }else {
                        System.out.println("id is wrong");
                    }

                }
            }else if(decision==3){
                System.out.println("1.Login\n2.exit");
                int decision1=scan.nextInt();
                scan.nextLine();
                if(decision1==1){
                    System.out.println("Admin Id");
                    int id=scan.nextInt();
                    scan.nextLine();
                    System.out.println("password");
                    String password=scan.nextLine();
                    if(driver.checkAdminId(id,password)) {
                        while (true){
                            System.out.println("1.Registered Students\n2.Registered Faculty\n3.Students request\n4.FacultyRequest\n5.Delete question/answer\n6.exit");
                            int decision2=scan.nextInt();
                            scan.nextLine();
                            if(decision2==1){
                                System.out.println(driver.getRegisteredStudents());
                            }else if(decision2==2){
                                System.out.println(driver.getRegisteredFaculties());
                            }else if(decision2==3){
                                System.out.println(driver.getPendingStudents());
                                System.out.println("Are you accept Students(1.Yes/2.No)");
                                int decision3=scan.nextInt();
                                scan.nextLine();
                                while (decision3==1){
                                    System.out.println("Enter the  Student Id");
                                    int studentId=scan.nextInt();
                                    driver.acceptStudent(studentId);
                                    System.out.println("1.continue accepting\n2.exit");
                                    decision3=scan.nextInt();
                                    scan.nextLine();
                                    System.out.println(driver.getPendingStudents());
                                }
                            }else if(decision2==4){
                                System.out.println(driver.getPendingFaculties());
                                System.out.println("Are you accept Faculty(1.Yes/2.No)");
                                int decision3=scan.nextInt();
                                scan.nextLine();
                                while (decision3==1){
                                    System.out.println("Enter the  faculty Id");
                                    int facultyId =scan.nextInt();
                                    driver.acceptFaculty(facultyId);
                                    System.out.println("1.continue accepting\n2.exit");
                                    decision3=scan.nextInt();
                                    scan.nextLine();
                                }
                            }else if(decision2==5){
                                System.out.println(driver.getComments());
                                System.out.println("enter the comment Id");
                                int commentId=scan.nextInt();
                                String result=driver.deleteComment(commentId);
                                System.out.println(result);
                            }else if(decision2==6){
                                break;
                            }
                        }

                    }else {
                        System.out.println("password or id is wrong");
                    }

                }else if(decision1==2){
                    break;
                }
            }
        }
    }

    private static void facultyLogin(Scanner scan, ProgramDriver driver, int id) {
        while (true){
            System.out.println("1.Edit Account\n2.Add StudyMaterials\n3.Delete Study Material\n4.view Doubt\n5.add Answers\n6.exit");
            int decision2= scan.nextInt();
            scan.nextLine();
            if(decision2==1){
                Faculty faculty= driver.getFaculty(id);
                editProfile(scan,faculty);
            }else if(decision2==2){
                System.out.println("Subject");
                String subject= scan.nextLine();
                System.out.println("Material");
                String material= scan.nextLine();
                driver.addMaterial(material,subject);
                System.out.println("Material Added");
            }else if(decision2==3){
                System.out.println("Subject");
                String subject= scan.nextLine();
                System.out.println("Material");
                String material= scan.nextLine();
                driver.deleteMaterial(material,subject);
                System.out.println("Material deleted");
            }else if(decision2==4){
                Map<Integer, List<Comment>> doubts= driver.getDoubts();
                System.out.println(doubts);
            }else if(decision2==5){
                System.out.println("Enter Question Id");
                int commentId= scan.nextInt();
                scan.nextLine();
                System.out.println("Answer");
                String question= scan.nextLine();
                driver.setAnswer(commentId,question, id);
            }else if(decision2==6){
                break;
            }
        }
    }

    private static Faculty getFaculty(String name, String password, String gender, int age, long mobile, String subject) {
        Faculty faculty=new Faculty();
        faculty.setAccessType("Faculty");
        faculty.setName(name);
        faculty.setGender(gender);
        faculty.setAge(age);
        faculty.setMobileNumber(mobile);
        faculty.setPassword(password);
        faculty.setSubject(subject);
        return faculty;
    }

    private static void studentLogin(Scanner scan, ProgramDriver driver, int id) {
        while (true){
            System.out.println("1.Edit Account\n2.View StudyMaterials\n3.Ask Doubt\n4.View Answers\n5.exit");
            int decision2= scan.nextInt();
            scan.nextLine();
            if(decision2==1){
                Student student= driver.getStudent(id);
                editProfile(scan,student);
            }else if(decision2==2){
                System.out.println("Subject");
                String subject= scan.nextLine();
                List<String>materials= driver.getMaterials(subject);
                System.out.println(materials);
            }else if(decision2==3){
                System.out.println("Question");
                String question= scan.nextLine();
                driver.addQuestion(question,id);
            }else if(decision2==4){
                Map<Integer, List<Comment>> answers= driver.getAnswers();
                System.out.println(answers);
            }else if(decision2==5){
                break;
            }
        }
    }

    private static void editProfile(Scanner scan,Login student) {

        while (true){
            System.out.println("1.Name\n2.Age\n3.mobile number\n4.gender\n5.password\n6.exit");
            int decision3= scan.nextInt();
            scan.nextLine();
            if(decision3==1){
                System.out.println("Name");
                student.setName(scan.nextLine());
            }else if(decision3==2){
                System.out.println("Age");
                student.setAge(scan.nextInt());
                scan.nextLine();
            }else if(decision3==3){
                System.out.println("Mobile");
                student.setMobileNumber(scan.nextLong());
                scan.nextLine();
            }else if(decision3==4){
                System.out.println("gender");
                student.setGender(scan.nextLine());
            }else if(decision3==5){
                System.out.println("Password");
                String password= scan.nextLine();
                student.setPassword(password);
            }else if(decision3==6){
                break;
            }
        }
    }

    private static Student getStudent(String name, String password, String gender, int age, long mobile, int standard) {
        Student student=new Student();
        student.setName(name);
        student.setAccessType("Student");
        student.setStandard(standard);
        student.setAge(age);
        student.setGender(gender);
        student.setMobileNumber(mobile);
        student.setPassword(password);
        return student;
    }
}
