package Util;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class Config {
    /**
     * This class contains the location of the resources.
     */
    // Initialize on first access

    
    public static final String DATA_PATH = Paths.get(System.getProperty("user.dir"), "Data").toString() + File.separator;
    public static final String FLAT_CSV = "FlatList.csv";
    public static final String MANAGER_CSV = "ManagerList.csv";
    public static final String OFFICER_CSV = "OfficerList.csv";
    public static final String PROJECT_CSV = "ProjectList.csv";
    public static final String ENQUIRY_CSV = "EnquiryList.csv";
    public static final String APPLICANT_CSV = "ApplicantList.csv";
    public static final String APPLICATION_CSV = "ApplicationList.csv";
    public static final String REGISTRATION_CSV = "RegistrationList.csv";
    public static final String SEARCH_SETTING_CSV = "SearchSettingList.csv";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int MAX_LINE_WIDTH = 80;
    public static final int MAX_SIMPLE_TEXT_LENGTH = 50;
}
