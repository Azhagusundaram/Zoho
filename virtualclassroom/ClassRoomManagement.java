package virtualclassroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRoomManagement {
    private Map<Integer,Student> studentDetails=new HashMap<>();
    private Map<Integer,Faculty> facultyDetails=new HashMap<>();
    private Map<Integer, Admin> adminDetails =new HashMap<>();
    private Map<String, List<String>> studyMaterials =new HashMap<>();
    private Map<Integer,List<Comment>>questions=new HashMap<>();
    private Map<Integer,List<Comment>>answers=new HashMap<>();
    private Map<Integer,Comment>comments=new HashMap<>();
    private Map<Integer,Student> signUpPendingStudents=new HashMap<>();
    private Map<Integer,Faculty>signUpPendingFaculty=new HashMap<>();
    private Map<Integer,Student> registeredStudents=new HashMap<>();
    private Map<Integer,Faculty>registeredFaculty=new HashMap<>();

    public void setStudentDetails(Student student){
        int studentId=student.getId();
        studentDetails.put(studentId,student);
        signUpPendingStudents.put(studentId,student);
    }

    public Map<Integer, Comment> getComments() {
        return comments;
    }

    public Map<Integer, Student> getSignUpPendingStudents() {
        return signUpPendingStudents;
    }

    public Map<Integer, Faculty> getSignUpPendingFaculty() {
        return signUpPendingFaculty;
    }

    public Map<Integer, Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public Map<Integer, Faculty> getRegisteredFaculty() {
        return registeredFaculty;
    }

    public void setFacultyDetails(Faculty faculty){
        int studentId= faculty.getId();
        facultyDetails.put(studentId, faculty);
        signUpPendingFaculty.put(studentId,faculty);
    }

    public Map<String, List<String>> getStudyMaterials() {
        return studyMaterials;
    }
    public Map<Integer, List<Comment>> getQuestions(){
        return questions;
    }
    public Map<Integer, List<Comment>> getAnswers(){
        return answers;
    }

    public void setRegisteredStudents(Student student) {
        int studentId=student.getId();
        registeredStudents.put(studentId,student);
    }

    public void setRegisteredFaculty(Faculty faculty) {
        int facultyId=faculty.getId();
        registeredFaculty.put(facultyId,faculty);
    }

    public void setQuestions(Comment question) {
        int studentId= question.getId();
        List<Comment>allQuestions=questions.get(studentId);
        if(allQuestions==null){
            allQuestions=new ArrayList<>();
            questions.put(studentId,allQuestions);
        }
        allQuestions.add(question);
        int commentId= question.getCommentId();
        comments.put(commentId, question);
    }

    public void setAnswers(int commentId, Comment answer) {
        List<Comment> allAnswers =answers.get(commentId);
        if(allAnswers ==null){
            allAnswers =new ArrayList<>();
            answers.put(commentId, allAnswers);
        }
        allAnswers.add(answer);
        int id=answer.getCommentId();
        comments.put(id,answer);
    }

    public void setMaterials(String material, String subject) {
        List<String>materials=studyMaterials.get(subject);
        if(materials==null){
            materials=new ArrayList<>();
            studyMaterials.put(subject,materials);
        }
        materials.add(material);
    }

    public Map<Integer, Student> getStudentDetails() {
        return studentDetails;
    }

    public Map<Integer, Faculty> getFacultyDetails() {
        return facultyDetails;
    }

    public Map<Integer, Admin> getAdminDetails() {
        return adminDetails;
    }

    public void setAdminDetails(Admin administration){
        int studentId= administration.getId();
        adminDetails.put(studentId, administration);
    }

}
