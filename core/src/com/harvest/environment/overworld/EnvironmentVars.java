package com.harvest.environment.overworld;

/**
 * Created by Patty on 1/24/2016.
 */
public class EnvironmentVars {

    public static final String TRANS_IMG_PATH = "rock.png";


    public static final short HOUSE = 0000; //house will always be 0000 no matter the zone

    //Zones refer to what overall area you are currently in
    public static final short ZONE_CITY = 0001;
    public static final short ZONE_SUBURB = 0010;
    public static final short ZONE_FARM = 0011;

    //Areas are places inside the big zones. Each area in a zone must have a unique ID
    public static final short SUBURB_METRO = 0001;
    public static final short SUBURB_STRIP = 0010;
    public static final short SUBURB_HOUSING = 0011;
    public static final short SUBURB_WORK = 0100;

    public static final short FARM_SHOPS = 0001 ;
    public static final short FARM_HOUSE = 0010;
    public static final short FARM_BEACH = 0011;

    public static final short CITY_TOP_LEFT = 0001;
    public static final short CITY_TOP_RIGHT = 0010;
    public static final short CITY_BOTTOM_LEFT = 0011;
    public static final short CITY_BOTTOM_RIGHT = 0100;

}
