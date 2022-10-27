package test.graham.entities;

public class Employee {

    private int id; //
    private String fname; //For strings the default value is "" (an empty string)
    private String lname; //For strings the default value is "" (an empty string)
    private String email;
    private String passwd; //For strings the default value is "" (an empty string)

    private int isAdmin;


    public Employee() {
    }

    public Employee(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public Employee(String fname, String lname, String email, String passwd, int isAdmin) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.passwd = passwd;
        this.isAdmin = isAdmin;
    }

    public Employee(int id, String fname, String lname, String email, String passwd, int isAdmin) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.passwd = passwd;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFname() {return fname;}

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {return lname;}

    public void setLname(String lname) {this.lname = lname;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPasswd() {return passwd;}

    public void setPasswd(String passwd) {this.passwd = passwd;}

    public int getIsAdmin() {return isAdmin;}

    public void setIsAdmin(int isAdmin) {this.isAdmin = isAdmin;}
}
