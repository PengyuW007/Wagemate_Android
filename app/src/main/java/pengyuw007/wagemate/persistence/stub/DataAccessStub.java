package pengyuw007.wagemate.persistence.stub;

import java.util.ArrayList;

import pengyuw007.wagemate.application.Main;
import pengyuw007.wagemate.objects.Job;
import pengyuw007.wagemate.objects.User;
import pengyuw007.wagemate.persistence.IPersistenceAccess;

public class DataAccessStub implements IPersistenceAccess {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<User> users;
    private ArrayList<Job> jobs;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    @Override
    public void open(String dbPath) {
        users = new ArrayList<>();
        jobs = new ArrayList<>();

        User user;
        Job job;

        user = new User(100, "Ann", "A");
        users.add(user);
        user = new User(200, "Bob", "B");
        users.add(user);
        user = new User(300, "Cathy", "C");
        users.add(user);

        job = new Job("a1", "Front-end Dev");
        jobs.add(job);
        job = new Job("b2", "Back-end Dev");
        jobs.add(job);
        job = new Job("c3", "Full stack Dev");
        jobs.add(job);

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    @Override
    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /*** Users ***/
    @Override
    public String addUser(User user) {
        String res = null;

        if (!isFound(user.getSin(), user.getName())) {
            users.add(user);
            res = "Success! New user " + user.getName() + ", SIN: " + user.getSin();
        } else {
            res = "Error! There is already a user with SIN: " + user.getSin() + ", Name: " + user.getName();
        }

        return res;
    }

    @Override
    public ArrayList<User> getUsers() {
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

        if (!found) {
            System.out.println("No such user named " + name + " exists!");
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
    public String rename(String name, String newName) {
        String res = null;
        User user = getUserByName(name);
        User anotherUser = getUserByName(newName);

        if (user != null) {
            if (anotherUser == null) {
                user.setName(newName);
                res = "Success! Rename success.";
            } else {
                res = "FAIL Rename! Cannot rename to " + newName + ", name already exists!";
            }
        } else {
            res = "FAIL Rename! No user named: " + name;
        }

        return res;
    }

    @Override
    public String reSin(String name, long newSin) {
        String res = null;
        User user = getUserByName(name);

        if (user != null) {
            if (!sinConflict(newSin)) {
                user.setSin(newSin);
            } else {
                res = "FAIL Re-SIN! SIN: " + newSin + " already exists.";
            }
        } else {
            res = "FAIL Re-SIN! No user named: " + name + " to re-Sin.";
        }
        return res;
    }

    @Override
    public String rePassword(String name, String newPassword) {
        String res = null;
        User user = getUserByName(name);
        if (user != null) {
            user.setPWD(newPassword);
        } else {
            res = "FAIL! No user named: " + name;
        }
        return res;
    }


    @Override
    public boolean deleteUser(String name) {
        boolean isRemoved = false;
        User user = getUserByName(name);

        if (user != null) {
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

        if (getJobByURL(newJob.getURL()) == null) {
            jobs.add(newJob);
            res = "Success! New job: " + newJob.getPosition() + ", URL: '" + newJob.getURL()+"'";
        } else {
            res = "FAILED! Position: " + newJob.getPosition() + ", URL: '" + newJob.getURL() + "' already exists.";
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
            if (jobs.get(i).getURL().equals(url)) {
                job = jobs.get(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No such job with url: '" + url + "' exists!");
        }
        return job;
    }

    @Override
    public boolean isMatchJob(String url, String name) {
        boolean match = false;

        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getURL().equals(url) && jobs.get(i).getPosition().equals(name)) {
                match = true;
            }
        }
        return match;
    }

    @Override
    public String rePosition(String url, String name) {
        String res;
        Job job = getJobByURL(url);

        if (job != null) {
            job.setPosition(name);
            res = "Success! Updated position.";
        } else {
            res = "FAIL! Not found position: " + name;
        }
        return res;
    }

    @Override
    public String reURL(String url, String newURL) {
        String res;
        Job job = getJobByURL(url);
        Job newJob = getJobByURL(newURL);

        if (job != null) {
            if(newJob==null){
                job.setURL(newURL);
                res = "Success! Updated URL.";
            }else{
                res ="FAIL! URL: '"+newURL+"' already exists.";
            }
        } else {
            res = "FAIL! No URL: '" + url+"'";
        }
        return res;
    }

    @Override
    public boolean deleteJob(String url) {
        boolean isRemoved = false;
        Job job = getJobByURL(url);

        if (job != null) {
            jobs.remove(job);
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public void clearJobs() {
        jobs.clear();
    }

    private boolean isFound(long sin, String name) {
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getSin() == sin && currUser.getName().equals(name)) {
                found = true;
            }
        }

        return found;
    }

    private boolean sinConflict(long sin) {
        boolean conflict = false;

        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getSin() == sin) {
                conflict = true;
            }
        }

        return conflict;
    }
}
