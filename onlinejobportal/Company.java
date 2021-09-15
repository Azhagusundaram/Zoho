package onlinejobportal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {


    private int companyId;
    private String name;
    private String password;
    private String address;
    private Map<String, Job> vacancyJobs=new HashMap<>();
    private Map<String,List<String>>appliedList=new HashMap<>();
    private Map<String,List<Job>>selectedList=new HashMap<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public void setSelectedList(String applicantName,Job job){
        List<Job>jobs=selectedList.get(applicantName);
        if(jobs==null){
            jobs=new ArrayList<>();
            selectedList.put(applicantName,jobs);
        }
        jobs.remove(job);
        jobs.add(job);
    }
    public Map<String, List<Job>> getSelectedList(){
        return selectedList;
    }
    public void addVacancyJobs(String jobName,Job job){
        vacancyJobs.put(jobName,job);
    }
    public void addApplicantList(String applicantName,String jobName){
        List<String>jobs=appliedList.get(applicantName);
        if(jobs==null){
            jobs=new ArrayList<>();
            appliedList.put(applicantName,jobs);
        }
        jobs.remove(jobName);
        jobs.add(jobName);
    }
    public Map<String, List<String>> getAppliedList(){
        return appliedList;
    }
    public Job getJobDetails(String jobName){
        return vacancyJobs.get(jobName);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
