package virtualclassroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VirtualClassRoomDriver {
    int studentId=1;
    int facultyId=1;
    int adminId=1;
    int commentId=1;
    int questionId=1;
    ClassRoomManagement cache=new ClassRoomManagement();
    public int addStudentAccount(Student student){
        student.setId(studentId);
        cache.setStudentDetails(student);
        studentId++;
        return studentId-1;
    }
    public void acceptStudent(int studentId){
        Student student=cache.getStudentDetails().get(studentId);
        if(student!=null){
            student.setStatus("Access allowed");
            cache.setRegisteredStudents(student);
            cache.getSignUpPendingStudents().remove(studentId);
        }

    }
    public void acceptFaculty(int facultyId){
        Faculty faculty =cache.getFacultyDetails().get(facultyId);
        if(faculty!=null){
            faculty.setStatus("Access allowed");
            cache.setRegisteredFaculty(faculty);
            cache.getSignUpPendingFaculty().remove(facultyId);
        }

    }
    public Map<Integer, Student> getPendingStudents(){
        return cache.getSignUpPendingStudents();
    }
    public Map<Integer, Faculty> getPendingFaculties(){
        return cache.getSignUpPendingFaculty();
    }
    public String checkStudentStatus(int studentId){
        Student student=cache.getStudentDetails().get(studentId);
        return student.getStatus();
    }
    public String checkFacultyStatus(int facultyId){
        Faculty faculty=cache.getFacultyDetails().get(facultyId);
        return faculty.getStatus();
    }

    public int addFacultyAccount(Faculty faculty){
        faculty.setId(facultyId);
        cache.setFacultyDetails(faculty);
        facultyId++;
        return facultyId-1;
    }
    public int addAdminAccount(Admin admin){
        admin.setId(adminId);
        cache.setAdminDetails(admin);
        adminId++;
        return adminId-1;
    }
    public List<String> getMaterials(String subject){
        List<String> materials=cache.getStudyMaterials().get(subject);
        if(materials==null){
            materials=new ArrayList<>();
            materials.add("Subject name is wrong");
        }
        return materials;
    }
    public void addMaterial(String material,String subject){
        cache.setMaterials(material,subject);
    }
    public void deleteMaterial(String material,String subject){
        List<String>materials=cache.getStudyMaterials().get(subject);
        materials.remove(material);
    }
    public Student getStudent(int studentId){
        Student student=cache.getStudentDetails().get(studentId);
        return student;
    }
    public Faculty getFaculty(int facultyId){
        Faculty faculty=cache.getFacultyDetails().get(facultyId);
        return faculty;
    }
    public void addQuestion(String question,int id){
        Comment comment=new Comment();
        comment.setComment(question);
        comment.setId(id);
        comment.setType("Question");
        comment.setCommentId(commentId);
        comment.setQuestionId(questionId);
        cache.setQuestions(comment);
        commentId++;
        questionId++;
    }
    public Map<Integer, Comment> getComments(){
        return cache.getComments();
    }
    public Map<Integer, Faculty> getRegisteredFaculties(){
        return cache.getRegisteredFaculty();
    }
    public Map<Integer, Student> getRegisteredStudents(){
        return cache.getRegisteredStudents();
    }
    public void setAnswer(int commentId,String answer,int id){
        Comment comment=new Comment();
        comment.setCommentId(this.commentId);
        comment.setComment(answer);
        comment.setId(id);
        comment.setType("Answer");
        comment.setQuestionId(commentId);
        cache.setAnswers(commentId,comment);
        this.commentId++;
    }
    public String deleteComment(int commentId){
        Comment comment=cache.getComments().get(commentId);
        if(comment!=null){
            String type=comment.getType();
            if(type.startsWith("Q")){
                int studentId=comment.getId();
                deleteQuestion(commentId,studentId);
            }else if(type.startsWith("A")){
                int questionId=comment.getQuestionId();
                deleteAnswer(questionId,commentId);
            }
            cache.getComments().remove(commentId);
            return "Deleted";
        }
        return "Comment id is wrong";
    }
    public void deleteQuestion(int index,int studentId){
        List<Comment> questions= cache.getQuestions().get(studentId);
        for(Comment comment:questions){
            int commentId=comment.getCommentId();
            if(commentId==index){
                questions.remove(comment);
                if(questions.isEmpty()){
                    cache.getQuestions().remove(questionId);
                }
                break;
            }
        }

    }
    public void deleteAnswer(int questionId,int index){
        List<Comment>answers=cache.getAnswers().get(questionId);
        for(Comment comment:answers){
            int commentId=comment.getCommentId();
            if(commentId==index){
                answers.remove(comment);
                break;
            }
        }
    }
    public Map<Integer, List<Comment>> getAnswers(){
        return cache.getAnswers();
    }
    public Map<Integer, List<Comment>> getDoubts(){
        return cache.getQuestions();
    }
    public boolean checkStudentId(int studentId, String password){
        Student student=cache.getRegisteredStudents().get(studentId);
        if(student==null){
            return false;
        }
        String oldPassword=student.getPassword();
        if(password.equals(oldPassword)){
            return true;
        }
        return false;
    }
    public boolean checkStudentId(int studentId){

        return cache.getStudentDetails().containsKey(studentId);
    }
    public boolean checkFacultyId(int facultyId){

        return cache.getFacultyDetails().containsKey(facultyId);
    }
    public boolean checkFacultyId(int facultyId, String password){
        Faculty faculty =cache.getRegisteredFaculty().get(facultyId);
        if(faculty ==null){
            return false;
        }
        String oldPassword= faculty.getPassword();
        if(password.equals(oldPassword)){
            return true;
        }
        return false;
    }
    public boolean checkAdminId(int adminId, String password){
        Admin admin =cache.getAdminDetails().get(adminId);
        if(admin ==null){
            return false;
        }
        String oldPassword= admin.getPassword();
        if(password.equals(oldPassword)){
            return true;
        }
        return false;
    }
}
