package com.xuanthuy.springbootdemo.form;

public class UserInfoForm {
	private String userName;
	private String firstName;
	private Byte gender;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
	private Integer enable;
	private Integer countryCode;
	public UserInfoForm(String userName, String firstName, Byte gender, String lastName, String password,
			String confirmPassword, String email, Integer enable, Integer countryCode) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.gender = gender;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.enable = enable;
		this.countryCode = countryCode;
	}
	/*
		Lay cac thong tin tu mang xa hoi
		public UserInfoForm(Connection<?> connection){
      UserProfile socialUserProfile = connection.fetchUserProfile();
        this.userId = null;
        this.email = socialUserProfile.getEmail();
        this.userName = socialUserProfile.getUsername();
        this.firstName = socialUserProfile.getFirstName();
        this.lastName = socialUserProfile.getLastName();
 
        ConnectionKey key = connection.getKey();
        // google, facebook, twitter
        this.signInProvider = key.getProviderId();
 
        // ID of User on google, facebook, twitter.
        // ID của User trên google, facebook, twitter.
        this.providerUserId = key.getProviderUserId();		}
	 */
	public UserInfoForm() {
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
}
