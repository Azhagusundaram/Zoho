package onlinejobportal;

import java.util.*;

public class Applicant {
    private  int applicantId;


    private String name;
    private String password;
    private Long mobileNumber;
    private String address;
    private String qualification;
    private Map<Integer, List<Integer>>appliedJobs=new HashMap<>();
    private Map<Integer,List<Integer>>selectedJobs=new HashMap<>();

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public void addSelectedJobs(int companyId,int jobId){
        List<Integer>jobs=selectedJobs.get(companyId);
        if(jobs==null){
            jobs=new ArrayList<>();
            selectedJobs.put(companyId,jobs);
        }
        jobs.remove(jobId);
        jobs.add(jobId);
    }
    public Map<Integer, List<Integer>> getSelectedJobs(){
        return selectedJobs;
    }
    public Map<Integer, List<Integer>> getAppliedJobs(){
        return appliedJobs;
    }
    public void applyJob(int companyId,int jobId){
        List<Integer>jobs=appliedJobs.get(companyId);
        if(jobs==null){
            jobs=new ArrayList<>();
            appliedJobs.put(companyId,jobs);
        }
        jobs.remove(jobId);
        jobs.add(jobId);
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

    public String toString(){
        return "Applicant Name :"+name+"\nApplicant Id :"+applicantId+" Location :"+address+" Qualification :"+qualification;
    }

}
