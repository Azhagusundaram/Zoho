package virtualclassroom;

public class Comment {
    private int id;
    private String comment;
    private int commentId;
    private String type;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    private int questionId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString(){
        return "\nComment id:"+commentId+" Question Id:"+questionId+" Student/Faculty Id: "+id+" Comment Type : "+type+"\nComment :"+comment;
    }
}
