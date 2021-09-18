package onlinejobportal;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ApplicantProgramDriver applicantDriver=new ApplicantProgramDriver();
        CompanyProgramDriver companyDriver= new CompanyProgramDriver();
        while (true){
            System.out.println("1.Applicant\n2.Company\n3.Exit");
            int decision=scan.nextInt();
            if(decision==1){

                while (true){
                    System.out.println("1.New Applicant\n2.Existing Applicant\n3.Exit");
                    int decision1=scan.nextInt();
                    scan.nextLine();
                    if(decision1==1){
                        Applicant applicant = getApplicant(scan);
                        int result= applicantDriver.addApplicant(applicant);
                        System.out.println("Applicant Id is "+result);
                    }else if(decision1==2){
                        System.out.println("Applicant Id");
                        int applicantId=scan.nextInt();
                        scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(applicantDriver.checkCredentials(applicantId,password)){
                            while (true){
                                System.out.println("1.View and apply Jobs\n2.view Applied Jobs\n3.view Selected Jobs\n4.Exit");
                                int decision2=scan.nextInt();
                                scan.nextLine();
                                if(decision2==1){
                                    viewAndApplyJobs(scan, applicantDriver, applicantId);
                                }else if(decision2==2){
                                    Map<Integer, List<Integer>> appliedJobs= applicantDriver.getAppliedJobs(applicantId);
                                    if(!appliedJobs.isEmpty()){
                                        System.out.println(appliedJobs);
                                    }else {
                                        System.out.println("You not applied for any jobs yet");
                                    }

                                }else if(decision2==3) {
                                    Map<Integer, List<Integer>> selectedJobs = applicantDriver.getSelectedJobs(applicantId);
                                    if(!selectedJobs.isEmpty()){
                                        System.out.println(selectedJobs);
                                    }else {
                                        System.out.println("You not selected for any jobs yet");
                                    }
                                }
                                else if(decision2==4){
                                    break;
                                }else {
                                    System.out.println("Invalid Input");
                                }
                            }

                        }else {
                            System.out.println("Name or password is wrong");
                        }
                    }else if(decision1==3){
                        break;
                    }else {
                        System.out.println("Invalid Input");
                    }
                }

            }else if(decision==2){
                while (true){

                    System.out.println("1.New Company Account\n2.Existing Company Account\n3.Exit");
                    int decision1=scan.nextInt();
                    scan.nextLine();
                    if(decision1==1){
                        Company company = getCompany(scan);
                        int result=companyDriver.addCompany(company);
                        System.out.println("Company id is "+result);
                    }else if (decision1==2){
                        System.out.println("Company Id");
                        int companyId=scan.nextInt();
                        scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(companyDriver.checkCredentials(companyId,password)){
                            while (true){
                                System.out.println("1.Add Jobs\n2.View and select applicant\n3.view Selected List\n4.Exit");
                                int decision2=scan.nextInt();
                                scan.nextLine();
                                if(decision2==1){
                                    addJob(scan, companyDriver, companyId);
                                }else if(decision2==2){
                                    ViewAndSelectApplicant(scan, companyDriver, companyId);
                                }else if(decision2==3){
                                    Map<Integer, List<Integer>> selectedList=companyDriver.getSelectedApplicants(companyId);
                                    if(!selectedList.isEmpty()){
                                        System.out.println(selectedList);
                                    }else {
                                        System.out.println("Not selected any candidates");
                                    }

                                }else if(decision2==4){
                                    break;
                                }else {
                                    System.out.println("Invalid Input");
                                }
                            }

                        }else {
                            System.out.println("Name or Password is wrong");
                        }
                    }else if(decision1==3){
                        break;
                    }else {
                        System.out.println("Invalid Input");
                    }
                }

            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }

    }

    private static void ViewAndSelectApplicant(Scanner scan, CompanyProgramDriver companyDriver, int companyId) {
        Map<Applicant, List<Job>> applicationList= companyDriver.getApplicationList(companyId);
        System.out.println(applicationList);
        if(!applicationList.isEmpty()){
            System.out.println("1.Are you select any Candidate\n2.exit");
            int decision3= scan.nextInt();
            if(decision3==1){
                System.out.println("Enter Applicant Id");
                int applicantId= scan.nextInt();
                System.out.println("Enter Job Id");
                int jobId= scan.nextInt();
                boolean result=companyDriver.selectApplicant(applicantId,jobId,companyId);
                if(result){
                    System.out.println("SelectedSuccessfully");
                }else {
                    System.out.println("Applicant Id or Job Id is wrong");
                }
            }
        }else {
            System.out.println("No candidates apply");
        }

    }

    private static void addJob(Scanner scan, CompanyProgramDriver companyDriver, int companyId) {
        System.out.println("Enter job Name :");
        String jobName= scan.nextLine();
        System.out.println("Qualification :");
        String qualification= scan.nextLine();
        System.out.println("Vacancies :");
        int vacancy= scan.nextInt();
        scan.nextLine();
        Job job = getJob(jobName, qualification, vacancy);
        companyDriver.addJob(job, companyId);
    }

    private static Company getCompany(Scanner scan) {
        System.out.println("Company Name :");
        String name= scan.nextLine();
        System.out.println("Password :");
        String password= scan.nextLine();
        password=checkPassword(scan, password);
        System.out.println("Address");
        String address= scan.nextLine();
        Company company = getCompany(name, password, address);
        return company;
    }

    private static void viewAndApplyJobs(Scanner scan, ApplicantProgramDriver applicantDriver, int applicantId) {
        List<Job> allJobs= applicantDriver.getJobs();
        if(!allJobs.isEmpty()){
            System.out.println(allJobs);
            System.out.println("1.View any company Details\n2.exit");
            int decision3= scan.nextInt();
            if(decision3==1){
                System.out.println("company Id");
                int companyId=scan.nextInt();
                scan.nextLine();
                String result=applicantDriver.getCompanyDetails(companyId);
                System.out.println(result);
            }
            System.out.println("1.Are you apply Any Job\n2.exit");
            decision3= scan.nextInt();
            if(decision3==1){
                System.out.println("Enter Job Id :");
                int jobId= scan.nextInt();
                boolean result=applicantDriver.applyJob(jobId, applicantId);
                if(result){
                    System.out.println("Applied Successfully");
                }else {
                    System.out.println("Job id is wrong");
                }

            }
        }else {
            System.out.println("Sorry no jobs are available");
        }

    }

    private static Applicant getApplicant(Scanner scan) {
        System.out.println("Name :");
        String name= scan.nextLine();
        System.out.println("Password :");
        String password= scan.nextLine();
        password=checkPassword(scan, password);
        System.out.println("Address");
        String address= scan.nextLine();
        System.out.println("Qualification :");
        String qualification= scan.nextLine();
        System.out.println("Mobile Number");
        long mobile= scan.nextLong();
        scan.nextLine();
        Applicant applicant = getApplicant(name, password, address, qualification, mobile);
        return applicant;
    }

    private static Job getJob(String jobName, String qualification, int vacancy) {
        Job job=new Job();
        job.setJobName(jobName);
        job.setVacancy(vacancy);
        job.setEligibilityCriteria(qualification);
        return job;
    }

    private static Company getCompany(String name, String password, String address) {
        Company company=new Company();
        company.setName(name);
        company.setPassword(password);
        company.setAddress(address);
        return company;
    }

    private static Applicant getApplicant(String name, String password, String address, String qualification, long mobile) {
        Applicant applicant=new Applicant();
        applicant.setAddress(address);
        applicant.setMobileNumber(mobile);
        applicant.setName(name);
        applicant.setPassword(password);
        applicant.setQualification(qualification);
        return applicant;
    }

    private static String checkPassword(Scanner scan, String password) {
        while (!Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%&]).{8,15}", password)){
            System.out.println("Enter correct password :");
            password = scan.nextLine();
        }
        return password;
    }


}
