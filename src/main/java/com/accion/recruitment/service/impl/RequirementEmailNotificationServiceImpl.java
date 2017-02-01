package com.accion.recruitment.service.impl;

import com.accion.recruitment.common.constants.EmailNotificationConstants;
import com.accion.recruitment.common.helper.EmailNotificationHelper;
import com.accion.recruitment.common.helper.SettingsHelper;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.RequirementEmailNotificationService;
import com.accion.recruitment.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 30/01/17 00:11 AM#$
 */

@Service("requirementEmailNotificationService")
public class RequirementEmailNotificationServiceImpl implements RequirementEmailNotificationService{


    @Autowired
    private EmailNotificationHelper emailNotificationHelper;

    @Autowired
    private SettingsService settingsService;

    private SettingsHelper settingsHelper;

    @Override
    public  Boolean  sendRequirementOpenStatus(Positions requirements,List<String> toUser){
        String subject = "Requirement: "+requirements.getJobTitle()+" is re-opened";
        String body = "<html><head>";
        body += "<style>table {border-collapse: collapse;} table, td, th {border: 2px solid black;}</style></head>";
        body += "Hello Team, ";
        body += "<br/><br/>";
        body += "This is a system generated mail to notify the reopening of  Requirement :"+requirements.getJobTitle()+".<br/><br/>";
        body += "You can resume corresponding regular activity for the requirement.<br/><br/>";
        body += "For more details you can contact your Account Manager or Admin. <br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser,this.getSettingsMap().get("from"),subject,body);

    }

    @Override
    public  Boolean  sendRequirementCloseStatus(Positions requirements,List<String> toUser){
        String subject = "Requirement: "+requirements.getJobTitle()+" is closed";
        String body = "<html><head>";
        body += "Hello Team";
        body += "<br/><br/>";
        body += "This is a system generated mail to notify the closure of Requirement : "+requirements.getJobTitle()+".<br/><br/>" +
                " There would not be any more activity for this requirement.<br/><br/>";
        body += "For more details you can contact your Account Manager or Admin.<br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser,this.getSettingsMap().get("from"),subject,body);

    }

    @Override
    public  Boolean  sendRequirementMailToTs(Positions requirements,User toUser){
        String subject = "Add Questions for New Requirement : "+requirements.getJobTitle();
        String body = "<html><head>";
        body += "Hello "+toUser.getFirstName()+",";
        body += "<br/><br/>";
        body += "A new Requirement : "+requirements.getJobTitle()+" is created for the Client: "+requirements.getClientName()+".<br/><br/>" ;
        body += "We request you to add appropriate questions for this requirement.<br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser.getEmailId(),this.getSettingsMap().get("from"),subject,body);

    }


    @Override
    public  Boolean  sendRequirementMailToRecruiter(Positions requirements,User toUser){
        String subject = "Add Candidate for New Requirement : "+requirements.getJobTitle();
        String body = "<html><head>";
        body += "Hello "+toUser.getFirstName()+",";
        body += "<br/><br/>";
        body += "A new Requirement : "+requirements.getJobTitle()+" is created for the Client: "+requirements.getClientName()+".<br/><br/>" ;
        body += "You will be able to add candidates when the Questionnaire for this interview is ready.<br/><br/>";
        body += "Please login to the application using your user id & password using the application link provided below.<br/></br>";
        body+=this.getSettingsMap().get("link");
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(toUser.getEmailId(),this.getSettingsMap().get("from"),subject,body);

    }


    @Override
    public  Boolean  sendCandidateMail(List<String> bccUser,Positions requirements,Candidates candidates,PositionCandidates positionCandidates){
        String subject = "AccionLabs would like you to complete video interview";
        String body = "<html><head>";
        body += "Hello "+candidates.getCandidateName()+",<br/><br/>";
        body += "Accion Labs invites you to complete a video interview for the requirement of: "+requirements.getJobTitle()+ "<br/><br/>";
        body += "The required skills are: <br/>";
        body += "<ol>";

        String[] primarySkills=requirements.getPrimarySkill().split(",");

        for(int i=0;i<primarySkills.length;i++){
            body +="<li>"+primarySkills[i]+"</li><br/>";
        }

        body += "</ol>";
        body += "Job Description: "+requirements.getJobDescription()+"<br/><br/>";
        body += "Your Interview will consist of a set of prerecorded questions. You need to answer it using your<br/><br/>computer webcam and microphone. Please complete the interview at your earliest convenience.<br/><br/>";
        body += "Here are a few easy things you can do to succeed in a video interview for an employer:<br/><br/>";
        body +="<ol>";
        body +="<li>Pick a quiet and Clean place to record your video.</li>";
        body +="<li>Dress professionally.</li>";
        body +="<li>Look into the Camera and talk clearly, slowly and accentuate your words.</li>";
        body +="<li>Read the job description provided by the recruiter prior to the interview.<br/>(Prepare! Make sure to write down a few notes before you start so that you can be concise and make a strong point).</li>";
        body +="<li>Don't read off the page. Even if you've jotted down ideas, try not to be too scripted.</li>";
        body +="<li>Be excited! The biggest mistake you can make is to seem sleepy, unmotivated or bored. Engage the employer by seeming excited and energetic.</li>";
        body +="<li>Hiring managers might view the videos so please interview as if you are interviewing with the Hiring manager.</li>";
        body +="<li>While responding to questions, give examples when and where you encountered with similar challenges.</li>";
        body +="</ol>";

        body += "" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<title>Video in Email Test</title>\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "     \n" +
                "  @media screen and (max-width:800px) {\n" +
                "    div[class=video_holder] {display:none;}\n" +
                "    div[class=android] {display:block !important;width:320px !important;height:176px !important;}\n" +
                "  }\n" +
                "\n" +
                "  @media screen and (width:320px), screen and (width:703px){\n" +
                "    div[class=android] {display:none !important;}  \n" +
                "    div[class=video_holder] {display:block !important;}\n" +
                "  }\n" +
                "\n" +
                "  .ExternalClass div.video_holder {display:none !important;}\n" +
                "  .ExternalClass div.android {display:block !important;width:100% !important;height:200px !important;}\n" +
                "\n" +
                "</style>\n" +

                "<div style=\"margin-left: 58px;\" class=\"video_holder\">\n" +

                "    <video width=\"200\" height=\"115\" controls>\n" +
                "        <source src=\"https://d2ue829my6ap69.cloudfront.net/video/HoT_video.mp4\" type=\"video/mp4\">\n" +

                "          "+this.getSettingsMap().get("INTERVIEW_VIDEO_GUIDE")+
                "            src=\"https://d2ue829my6ap69.cloudfront.net/images/dosDontPoster.jpg\" width=\"168\" /></a>\n" +
                "    </video>\n" +
                "</div>\n" +
                "<div class=\"android\" style=\"display:none; width:0px; height:0px; overflow:hidden;\">\n" +

                "   "+this.getSettingsMap().get("INTERVIEW_VIDEO_GUIDE")+"<img height=\"94\" \n" +
                "    src=\"https://d2ue829my6ap69.cloudfront.net/video/HoT_video.mp4\" width=\"168\" /></a>\n" +
                "</div>";

        body += "Note: <ol><li>Candidates are requested to use GOOGLE CHROME BROWSER to take the interview<br/>";
        body += "If you are not using Chrome please <a href=\"https://www.google.co.in/chrome/\"><b>Download</b></a> the browser and install before taking the Interview. </li><br/>";
        body += "<li>Use 'GET IN TOUCH WITH US' functionality to state your problems faced in the Interview process and regeneration of Interview link.</li></ol>";
        body += "To access your interview please click on the following link: <a href=\""+this.getSettingsMap().get("domain")+"/RecruitmentWeb/"+positionCandidates.getCandidateLink()+"/interview.do\"><b>Interview Link</b></a><br/><br/>";
        body += "If you have any questions, please contact: hookedontalent@accionlabs.com <br/><br/>";
        body += "All the Best !!!<br/><br/>";
        body+= EmailNotificationConstants.SIGNATURE;
        return this.emailNotificationHelper.sendMail(candidates.getEmailId(),bccUser,this.getSettingsMap().get("from"),subject,body);

    }

    public HashMap<String,String> getSettingsMap(){
        Settings settings=this.settingsService.findSettingsDetailsById(1);
        HashMap<String,String> settingMap=new HashMap<String, String>();
        String link= "Link: <a href=\""+settings.getDomainName()+""+ EmailNotificationConstants.CHANGE_PASSWORD_URL+"\">"+settings.getDomainName()+""+EmailNotificationConstants.CHANGE_PASSWORD_URL+"\"</a> <br/><br/><br/>";
        String INTERVIEW_VIDEO_GUIDE= " <a href=\""+settings.getDomainName()+""+ EmailNotificationConstants.INTERVIEW_URL+"\">"+settings.getDomainName()+""+EmailNotificationConstants.INTERVIEW_URL+"\"</a> <br/><br/><br/>";


        settingMap.put("from",settings.getEmailId());
        settingMap.put("domain",settings.getDomainName());
        settingMap.put("link",link);
        settingMap.put("INTERVIEW_VIDEO_GUIDE",INTERVIEW_VIDEO_GUIDE);

        return settingMap;
    }
}
