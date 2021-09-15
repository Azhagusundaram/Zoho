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
                        System.out.println("Applicant Number is "+result);
                    }else if(decision1==2){
                        System.out.println("Name");
                        String name=scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(applicantDriver.checkCredentials(name,password)){
                            while (true){
                                System.out.println("1.View and apply Jobs\n2.view Applied Jobs\n3.view Selected Jobs\n4.Exit");
                                int decision2=scan.nextInt();
                                scan.nextLine();
                                if(decision2==1){
                                    viewAndApplyJobs(scan, applicantDriver, name);
                                }else if(decision2==2){
                                    Map<String, List<Job>>appliedJobs= applicantDriver.getAppliedJobs(name);
                                    System.out.println(appliedJobs.toString());
                                }else if(decision2==3){
                                    Map<String, List<Job>>selectedJobs=applicantDriver.getSelectedJobs(name);
                                    System.out.println(selectedJobs);
                                }else if(decision2==4){
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
                        System.out.println("Name");
                        String name=scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(companyDriver.checkCredentials(name,password)){
                            while (true){
                                System.out.println("1.Add Jobs\n2.View and select applicant\n3.view Selected List\n4.Exit");
                                int decision2=scan.nextInt();
                                scan.nextLine();
                                if(decision2==1){
                                    addJob(scan, companyDriver, name);
                                }else if(decision2==2){
                                    ViewAndSelectApplicant(scan, companyDriver, name);
                                }else if(decision2==3){
                                    Map<String, List<Job>>selectedList=companyDriver.getSelectedApplicants(name);
                                    System.out.println(selectedList);
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

    private static void ViewAndSelectApplicant(Scanner scan, CompanyProgramDriver companyDriver, String name) {
        List<String>applicationList= companyDriver.getApplicationList(name);
        System.out.println(applicationList);
        System.out.println("1.more about application list\n2.exit");
        int decision3= scan.nextInt();
        if(decision3==1){
            System.out.println("Enter application number");
            int applicationNumber= scan.nextInt();
            String applicantName=getName(applicationList.get(applicationNumber-1));
            List<String>jobLists= companyDriver.getJobLists(name,applicantName);
            System.out.println(jobLists);
            System.out.println("1.Select Applicant\n2.Exit");
            int decision4= scan.nextInt();
            if(decision4==1){
                System.out.println("Enter the Job Number");
                int jobNumber= scan.nextInt();
                String jobName=getName(jobLists.get(jobNumber-1));
                String result= companyDriver.selectApplicant(applicantName,jobName, name);
                System.out.println(result);
            }
        }
    }

    private static void addJob(Scanner scan, CompanyProgramDriver companyDriver, String name) {
        System.out.println("Enter job Name :");
        String jobName= scan.nextLine();
        System.out.println("Qualification :");
        String qualification= scan.nextLine();
        System.out.println("Vacancies :");
        int vacancy= scan.nextInt();
        scan.nextLine();
        Job job = getJob(jobName, qualification, vacancy);
        companyDriver.addJob(job, name);
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

    private static void viewAndApplyJobs(Scanner scan, ApplicantProgramDriver applicantDriver, String name) {
        List<String> allJobs= applicantDriver.getJobs();
        System.out.println(allJobs);
        System.out.println("1.detailed about particular jobs\n2.exit");
        int decision3= scan.nextInt();
        if(decision3==1){
            System.out.println("Enter Job Number :");
            int jobNumber= scan.nextInt();
            String jobName=getName(allJobs.get(jobNumber-1));
            List<String>companies= applicantDriver.getCompany(jobName);
            System.out.println(companies);
            System.out.println("Enter company Number:");
            int companyNumber = scan.nextInt();
            String companyName=getName(companies.get(companyNumber -1));
            Job job= applicantDriver.getJobDetails(jobName,companyName);
            System.out.println(job);
            System.out.println("1.Apply Job\n2.Exit");
            int decision4= scan.nextInt();
            if(decision4==1){
                String result= applicantDriver.applyJob(jobName,companyName, name);
                System.out.println(result);
            }
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
    private static String getName(String name){
        String[]array=name.split("\\.");
        return array[1];
    }

}
