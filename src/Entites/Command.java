package Entites;

public class Command {
	
	private int idCde;
	private float totalPrice;
	private String dateCde;
	
	
	public Command(int idCde, float totalPrice, String date) {
		super();
		this.idCde = idCde;
		this.totalPrice = totalPrice;
		this.dateCde = date;
	}

	public Command(float totalPrice, String dateCde) {
		super();
		this.totalPrice = totalPrice;
		this.dateCde = dateCde;
	}



	public int getIdCde() {
		return idCde;
	}


	public void setIdCde(int idCde) {
		this.idCde = idCde;
	}


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getDateCde() {
		return dateCde;
	}


	public void setDateCde(String dateCde) {
		this.dateCde = dateCde;
	}


	@Override
	public String toString() {
		return "Command n ° : " + idCde;
	}
	
	

}
