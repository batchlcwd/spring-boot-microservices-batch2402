package com.elearn.app.config;

import java.io.File;
import java.nio.file.Files;

public class AppConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    public static final String DEFAULT_SORT_BY = "title";

    public static final String COURSE_BANNER_UPLOAD_DIR = "uploads" + File.separator + "courses" + File.separator + "banners";


    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static  final String ROLE_GUST="ROLE_GUEST";

    public static final String DEFAULT_PROFILE_PIC_PATH="uploads/default.jpg";
}
