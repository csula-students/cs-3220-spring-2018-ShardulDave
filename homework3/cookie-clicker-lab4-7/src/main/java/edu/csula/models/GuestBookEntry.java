package edu.csula.models;

public class GuestBookEntry {
    private  int id;
    private  String username;
    private  String comment;

    public GuestBookEntry(int id,String username,String comment){
        this.id=id;
        this.username=username;
        this.comment=comment;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment=comment;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}
