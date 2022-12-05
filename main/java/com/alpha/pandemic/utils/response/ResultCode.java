package com.alpha.pandemic.utils.response;

public enum  ResultCode implements CustomizeCode
{

    SUCCESS(200, "success"),
    ERROR(402, "error"),
    NOT_AUTH(401, "You don't have the permission Please contact the administrator！"),
    FAILED(400, "The operation has failed"),

    /**
     * params error：1000～1999
     */
    PARAM_NOT_VALID(1001, "The parameter is not valid"),
    PARAM_IS_BLANK(1002, "The parameter is empty"),
    PARAM_TYPE_ERROR(1003, "A parameter [type error has] occured"),
    PARAM_NOT_COMPLETE(1004, "The parameter is missing or incomplete"),
    PARAM_ERROR(1005, "A parameter error has occured"),

    /**
     * user error
     */
    USER_NOT_LOGIN(2001, "Log in dammmmmmmmmmmm!"),
    USER_ACCOUNT_EXPIRED(2002, "This account is expired"),
    USER_CREDENTIALS_ERROR(2003, "You have an credential error"),
    USER_CREDENTIALS_EXPIRED(2004, "You credential has expired"),
    USER_ACCOUNT_DISABLE(2005, ""),
    USER_ACCOUNT_LOCKED(2006, "Your account is locked Please contact the administrator！"),
    USER_ACCOUNT_NOT_EXIST(2007, "This account does not exist"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "This account does alredy exist"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "This account is used by other user"),
    USERLIST_NOT_FOUND(2010, "The user list is not found"),
    FORBIDDEN_ACCOUNT(2011, "This account is forbidden"),
    REST_ACCOUNT_PWD(2012, "Please reset your password"),
    ADD_ACCOUNT(2013, "Please add a new account"),
    EDIT_ACCOUNT(2014, "Please edit your account"),
    ACCOUNT_LIST_EMPTY(2015, "The account list is empty"),
    ACCOUNT_HAS_CHINESE(2016, "This account has chinese characters Please change it to latin and try it again"),
    USER_NOT_EXIST(2017, "This user does not exist"),
    USER_SESSION_INVALID(2018, "Your session has expired! Please log in again"),
    LOGOUT_SUCCESS(2019, "You have successfully logged in！"),
    LOGIN_SUCCESS(2020, "You have successfully looged ou！"),
    LOGIN_USERNAME_EMPTY(2021, "The username is empty! Please fill it in"),
    CURRENT_USER_CANNOT_DO(2022,"The current user can not do this operation"),
    CURRENT_USER_CANNOT_DELETE_ADMIN(2023,"The current user can not delete"),


    /**
     * department error
     */
    DEPARTMENT_NOT_EXIST(3001, "The department does not exist"),
    DEPARTMENT_ALREADY_EXIST(3002, "This department does already exist"),
    ADD_DEPT(3003, "To add a new department"),
    EDIT_DEPT(3004, "To edit the department"),
    DEL_DEPT_NOT_EMPTY(3005, "The current department can not be empty"),

    /**
     * service error
     */
    NO_PERMISSION(4001, "You do not have the permission"),
    PAGEINFO_NOT_FOUND(4002, "The page info has not been found"),
    ARITHMETIC(4003, "An arithmetic error has occured"),
    API_GET_ERROR(4004, "The get request failed"),
    REPORT_ERROR(4005, "The health check point has failed please try it again"),
    DOUBLE_REPORT(4006, "Today You have already checked out！"),
    PRODUCT_IN_STOCK_EMPTY(4007, "The product stock can not be empty"),
    PRODUCT_NOT_FOUND(4008,"The product is not found"),
    PRODUCT_WAIT_PASS(4009,"The product is loading to pass"),
    PRODUCT_STATUS_ERROR(4010,"The product status has an error"),
    PRODUCT_IN_STOCK_NUMBER_ERROR(4011,"The product quantity has failed"),
    PRODUCT_IS_REMOVE(4012,"The product has been remove"),

    /**
     * nav error
     */
    EXPORT_FAIL(5001, "Export has failed!"),

    /**
     * files
     */
    FILE_DELETE_FAIL(6001, "Deleting the file has failed! please try it again:"),
    FILE_UPLOAD_FAIL(6002, "Uploading the file has failed:"),

    /**
     * role
     */
    ADD_ROLE(7001, "Adding the role has failed！"),
    EDIT_ROLE(7002, "Editing the role has failed！"),
    DELETE_ROLE(7003, "Deleting the role has failed！"),
    ROLE_IDS_ERROR(7004, "Roles and Ids has an error，Please try it again！"),
    ROLE_IS_FORBIDDEN(7005, "This role is forbidden！"),
    ASSIGN_ROLES_ERROR(7006, "Assigning role has failed！"),
    REMOVE_ROLES_ERROR(7007, "Removing the role has failed，Please try it again！"),
    ROLE_NOT_EXIST(7008, "This role does not exist，try another！"),
    ROLE_ALL_IS_FORBIDDEN(7009, "All the roles are forbidden! , contact the "),
    DONT_HAVE_ANY_ROLE(7010, "You do not have any permission!, contact the administrator"),

    /**
     * menu
     */
    MENU_NOT_EXIST(8001, "This menu does not exist！"),
    MENU_FORBIDDEN(8002, "This menu is forbidden so can not assign a new menu！"),
    ASSIGN_MENUS_ERROR(8003, "Assingning the menu has failed！"),
    REMOVE_MENUS_ERROR(8004, "Removing the menu has failed！"),

    /**
     * token error
     */
    JWT_FORMAT_ERROR(9001, "The Json Web Token has failed！"),
    JWT_IS_EXPIRED(9002, "The Json Web Token is expired！"),
    JWT_NOT_CURRENT_LOGIN_USER(9003, "The curren user has no token！"),

    /**
     * system error
     */
    NOT_FOUND(9004, "The resource is not found！"),
    METHOD_NOT_SUPPERED(9005, "This method is not supported！"),
    SYSTEM_ERROR(9006, "An system error has occured！"),
    UNKNOW_ERROR(9007, "Unknown error has occured！Please contact the administrator")
    ;


    // THE ABOVE SEMI-COLON IS IMPORTANT
    public final Integer code;
    public final  String message;

    ResultCode(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode()
    {
        return code;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
