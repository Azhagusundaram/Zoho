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
    private List<Integer>vacancyJob=new ArrayList<>();
    private Map<Integer,List<Integer>>appliedList=new HashMap<>();
    private Map<Integer,List<Integer>>selectedList=new HashMap<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public boolean setSelectedList(int applicantId, int jobId){
        List<Integer>jobs=selectedList.get(applicantId);
        if(jobs==null){
            jobs=new ArrayList<>();
            selectedList.put(applicantId,jobs);
        }
        jobs.add(jobId);
        return true;
    }
    public Map<Integer, List<Integer>> getSelectedList(){
        return selectedList;
    }
    public void addVacancyJob(int jobId){
        vacancyJob.add(jobId);
    }
    public List<Integer> getVacancyJob(){
        return vacancyJob;
    }
    public boolean addApplicantList(int applicantId, int jobId){
        List<Integer>jobs=appliedList.get(applicantId);
        if(jobs==null){
            jobs=new ArrayList<>();
            appliedList.put(applicantId,jobs);
        }
        jobs.add(jobId);
        return true;
    }
    public void removeApplicantList(int applicantId,int jobId){
        List<Integer>jobs=appliedList.get(applicantId);
        jobs.remove(jobId);
        if(jobs.isEmpty()){
            appliedList.remove(applicantId);
        }
    }
    public Map<Integer, List<Integer>> getAppliedList(){
        return appliedList;
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
    public String getCompanyDetails(){
        return "Company Name :"+name+"\nCompany Id :"+companyId+" Location :"+address;
    }
}
