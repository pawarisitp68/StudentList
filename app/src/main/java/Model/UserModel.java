package Model;

/**
 * Created by User on 29/9/2560.
 */

public class UserModel {
    String id;
    String stu;
    String name;
    String room;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getStu(){
        return stu;
    }

    public void setStu(String stu){
        this.stu = stu;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRoom(){
        return room;
    }

    public void setRoom(String room){
        this.room = room;
    }

    @Override
    public String toString(){
        return getStu()+"\n"
                +getName()+"\n"
                +getRoom();
    }
}
