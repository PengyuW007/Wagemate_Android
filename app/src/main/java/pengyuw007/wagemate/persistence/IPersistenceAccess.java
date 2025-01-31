package pengyuw007.wagemate.persistence;

import java.util.ArrayList;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.objects.Job;

public interface IPersistenceAccess {
    /*** Data Access ***/
    void open(String dbPath);
    void close();
    /*******************************************************/
    /************************* USERS **********************/
    /*******************************************************/

    /*** CREATE ***/
    String addUser(User user);

    /*** READ ***/
    ArrayList<User> getUsers();
    User getUserByName(String name);
    boolean isMatch(String name,long sin, String password); // Find whether info of this user matched

    /*** UPDATE ***/
    String rename(String name, String newName);
    String rePassword(String name, String newPassword);
    String reSin(String name, long newSin);

    /*** DELETE ***/
    boolean deleteUser(String name);
    void clearUsers();

    /*******************************************************/
    /************************* Jobs ************************/
    /*******************************************************/
    /*** CREATE ***/
    String addJob(Job job);

    /*** READ ***/
    ArrayList<Job>getJobs();
    Job getJobByURL(String url);
    boolean isMatchJob(String url, String name); // Find whether info of this job matched

    /*** UPDATE ***/
    String rePosition(String url, String name);

    String reURL(String url, String newURL);

    /*** DELETE ***/
    boolean deleteJob(String url);

    void clearJobs();
}
