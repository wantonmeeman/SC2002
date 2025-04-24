package Util;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

/**
 * The type Config.
 */
public class Config {
    // Initialize on first access

    /**
     * This class contains the location of the resources.
     */
    public static final String DATA_PATH = Paths.get(System.getProperty("user.dir"), "Data").toString() + File.separator;//If files are not loading, change this to a dorect path
    /**
     * The constant FLAT_CSV.
     */
    public static final String FLAT_CSV = "FlatList.csv";
    /**
     * The constant MANAGER_CSV.
     */
    public static final String MANAGER_CSV = "ManagerList.csv";
    /**
     * The constant OFFICER_CSV.
     */
    public static final String OFFICER_CSV = "OfficerList.csv";
    /**
     * The constant PROJECT_CSV.
     */
    public static final String PROJECT_CSV = "ProjectList.csv";
    /**
     * The constant ENQUIRY_CSV.
     */
    public static final String ENQUIRY_CSV = "EnquiryList.csv";
    /**
     * The constant APPLICANT_CSV.
     */
    public static final String APPLICANT_CSV = "ApplicantList.csv";
    /**
     * The constant APPLICATION_CSV.
     */
    public static final String APPLICATION_CSV = "ApplicationList.csv";
    /**
     * The constant REGISTRATION_CSV.
     */
    public static final String REGISTRATION_CSV = "RegistrationList.csv";
    /**
     * The constant WITHDRAWAL_CSV.
     */
    public static final String WITHDRAWAL_CSV = "WithdrawalList.csv";
    /**
     * The constant SEARCH_SETTING_CSV.
     */
    public static final String SEARCH_SETTING_CSV = "SearchSettingList.csv";
    /**
     * The constant NEIGHBOURHOOD_CSV.
     */
    public static final String NEIGHBOURHOOD_CSV = "NeighbourhoodList.csv";
    /**
     * The constant DATE_FORMAT.
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * The constant MAX_LINE_WIDTH.
     */
    public static final int MAX_LINE_WIDTH = 80;
    /**
     * The constant MAX_SIMPLE_TEXT_LENGTH.
     */
    public static final int MAX_SIMPLE_TEXT_LENGTH = 50;
}
