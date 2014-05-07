package ListViewAdapter;

public class NewsItem  
{

	private String phoneNumber;
	private String text;
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	
	@Override
	public String toString()
	{
		return "PhoneNumber:" + phoneNumber + ", Text:" +  text ;
	}
}
