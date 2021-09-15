package onlinejobportal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Applicant {
    private  int applicantNumber;


    private String name;
    private String password;
    private Long mobileNumber;
    private String address;
    private String qualification;
    private Map<String, List<Job>>appliedJobs=new HashMap<>();
    private Map<String,List<Job>>selectedJobs=new HashMap<>();

    public int getApplicantNumber() {
        return applicantNumber;
    }

    public void setApplicantNumber(int applicantNumber) {
        this.applicantNumber = applicantNumber;
    }

    public void addSelectedJobs(String companyName,Job job){
        List<Job>jobs=selectedJobs.get(companyName);
        if(jobs==null){
            jobs=new ArrayList<>();
            selectedJobs.put(companyName,jobs);
        }
        jobs.remove(job);
        jobs.add(job);
    }
    public Map<String, List<Job>> getSelectedJobs(){
        return selectedJobs;
    }
    public Map<String, List<Job>> getAppliedJobs(){
        return appliedJobs;
    }
    public void applyJob(String companyName,Job job){
        List<Job>jobs=appliedJobs.get(companyName);
        if(jobs==null){
            jobs=new ArrayList<>();
            appliedJobs.put(companyName,jobs);
        }
        jobs.remove(job);
        jobs.add(job);
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

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }



}
