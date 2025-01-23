package pengyuw007.wagemate.persistence.stub;

import java.util.ArrayList;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.objects.Job;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessStub implements IPersistenceAccess {
    private String dbName;
    private String dbType ="stub";

    private ArrayList<User> users;
    private ArrayList<Job>jobs;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }
    public DataAccessStub()
    {
        this(Main.dbName);
    }

    @Override
    public void open(String dbPath) {
        users = new ArrayList<>();
        jobs =  new ArrayList<>();

        User user;
        Job job;

        user = new User(100,"Ann","A");
        users.add(user);
        user = new User(200,"Bob","B");
        users.add(user);
        user = new User(300,"Cathy","C");
        users.add(user);

        job = new Job("1","Front-end Dev");
        jobs.add(job);
        job = new Job("2","Back-end Dev");
        jobs.add(job);
        job = new Job("3","Full stack Dev");
        jobs.add(job);

        System.out.println("Opened "+dbType+" database "+dbName);
    }

    @Override
    public void close() {
        System.out.println("Closed "+dbType+" database "+dbName);
    }

    /*** Users ***/
    @Override
    public String addUser(User user) {
        String res = null;
        if (!isFound(user.getSin(), user.getName())) {
            users.add(user);
        } else {
            res = "There is already a user with SIN: " + user.getSin() + ", Name: " + user.getName();
        }
        return res;
    }

    @Override
    public ArrayList<User>getUsers(){
        return users;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                user = users.get(i);
                found = true;
            }
        }

        if (!found){
            System.out.println("No such user named "+name+" exists!");
        }
        return user;
    }

    @Override
    public boolean isMatch(String name, long sin, String password) {
        boolean isMatch = false;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getSin() == sin && user.getName().equals(name) && user.getPWD().equals(password)) {
                isMatch = true;
            }
        }
        return isMatch;
    }

    @Override
    public void rename(String name, String newName) {
        User user = getUserByName(name);

        if(user!=null){
            user.setName(newName);
        }
    }

    @Override
    public void rePassword(String name, String newPassword) {
        User user = getUserByName(name);
        if(user!=null){
            user.setPWD(newPassword);
        }
    }

    @Override
    public void reSin(String name, long newSin) {
        User user = getUserByName(name);
        if(user!=null){
            user.setSin(newSin);
        }
    }

    @Override
    public boolean deleteUser(String name) {
        boolean isRemoved = false;
        User user = getUserByName(name);

        if(user!=null){
            users.remove(user);
            isRemoved = true;
        }

        return isRemoved;
    }

    @Override
    public void clearUsers() {
        users.clear();
    }

    /********************************
    ************* Jobs **************
    ********************************/
    @Override
    public String addJob(Job newJob) {
        String res;

        if(getJobByURL(newJob.getURL())==null){
            jobs.add(newJob);
            res = "Success! "+newJob.getPosition()+", URL: "+newJob.getURL();
        }else{
            res = "FAILED! Position: " + newJob.getPosition() + ", URL: " + newJob.getURL();
        }

        return res;
    }

    @Override
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    @Override
    public Job getJobByURL(String url) {
        Job job = null;
        boolean found = false;
        for (int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getURL().equals(url)){
                job = jobs.get(i);
                found = true;
            }
        }

        if(!found){
            System.out.println("No such job with url: "+url+" exists!");
        }
        return job;
    }

    @Override
    public boolean isMatchJob(String url, String name) {
        boolean match = false;

        for (int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getURL().equals(url)&&jobs.get(i).getPosition().equals(name)){
                match = true;
            }
        }
        return match;
    }

    @Override
    public void renameJob(String url, String name) {
        Job job= getJobByURL(url);

        if(job!=null){
            job.setPosition(name);
        }
    }

    @Override
    public void reURL(String url, String newURL) {
        Job job = getJobByURL(url);

        if(job!=null){
            job.setURL(newURL);
        }
    }

    @Override
    public boolean deleteJob(String url) {
        boolean isRemoved = false;
        Job job = getJobByURL(url);

        if(job!=null){
            jobs.remove(job);
            isRemoved =true;
        }
        return isRemoved;
    }

    @Override
    public void clearJobs() {
        jobs.clear();
    }

    private boolean isFound(long sin, String name) {
        boolean found = true;

        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getSin() == sin && currUser.getName().equals(name)) {
                found = false;
            }
        }

        return found;
    }
}
