package pengyuw007.wagemate.persistence;

import java.util.ArrayList;
import java.util.List;

import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.objects.Job;

public interface IPersistenceAccess {
    /*** Data Access ***/
    void open(String dbPath);
    void close();
    /*******************************************************/
    /************************* USAERS **********************/
    /*******************************************************/

    /*** CREATE ***/
    String addUser(User user);

    /*** READ ***/
    User getUserByName(String name);

    boolean isMatch(String name,long sin, String password); // Find whether info of this user matched

    /*** UPDATE ***/
    void rename(String name, String newName);

    void rePassword(String name, String newPassword);

    void reSin(String name, long newSin);

    /*** DELETE ***/
    boolean deleteUser(String name);

    void clearUsers();

    /*******************************************************/
    /************************* Jobs ************************/
    /*******************************************************/
    /*** CREATE ***/
    String addJob(Job job);

    /*** READ ***/
    Job getJobByURL(String name);

    boolean isMatchJob(String url, String name); // Find whether info of this job matched

    /*** UPDATE ***/
    void renameJob(String url, String name);

    void reURL(String url, String newURL);

    /*** DELETE ***/
    boolean deleteJob(String url);

    void clearJobs();
}
