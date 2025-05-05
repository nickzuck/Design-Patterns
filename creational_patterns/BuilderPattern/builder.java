/*
You are creating a project for real estate company which will require to present different types of houses to their clients
The clients want customization of houses based on the budget of the end customer. 
For example : if it's a budget friendly house, it should contain only rooms, kitchens and bathrooms.
for a rich family it should contain a parking space as well
For a VIP it will contain swimming pool which we call as Villa


Write the code for company to ensure which can prepare the house as per client's requirements
*/

class House {
	public int rooms ; // number of rooms
	public String kitchen ; // type of kitche (modular, classic etc.)
	public int bathrooms ; // number of bathrooms
	public int parking ; // number of vehicles that can be parked
}

class GrandHouse extends House{
	public int swimmingPool ; //are of pool in metre square
	public int parking ; // number of vehicles that can be parked
}

interface HouseBuilder {
	public void createRoom(int rooms) ; 
	public void createKitchen(String typeOfKitchen) ;
	public void createBathroom(int bathrooms) ;
	public void createParking(int noOfCars) ;
}



class BudgetFriendlyHouse implements HouseBuilder{
	private House house ; 

	BudgetFriendlyHouse(){
		this.house = new House() ;
	}

	public void createRoom(int rooms){
		this.house.rooms = rooms ;
	}

	public void createKitchen(String typeOfKitchen){
		this.house.kitchen = typeOfKitchen ;
	}

	public void createBathroom(int number){
		this.house.bathrooms = number ;
	}

	public void createParking(int noOfCars){
		this.house.parking = noOfCars;
	}


	public House build(){
		System.out.println("This house has " + this.house.rooms + " rooms, " 
											 + this.house.kitchen + " kitchen, " 
											 + this.house.bathrooms + " bathroom"
											 + ",parking of " + this.house.parking + " cars");
		return this.house ;
	}
}

class Villa implements HouseBuilder{
	private GrandHouse house ; 

	Villa(){
		this.house = new GrandHouse() ;
	}

	public void createRoom(int rooms){
		this.house.rooms = rooms ;
	}

	public void createKitchen(String typeOfKitchen){
		this.house.kitchen = typeOfKitchen ;
	}

	public void createBathroom(int number){
		this.house.bathrooms = number ;
	}

	public void createParking(int noOfCars) {
		this.house.parking = noOfCars ;
	} 

	public void createSwimmingPool(int poolArea) {
		this.house.swimmingPool = poolArea ;
	}

	public GrandHouse build(){
		System.out.println("This house has " + this.house.rooms + " rooms, " 
											 + this.house.kitchen +  " kitchen, " 
											 + this.house.bathrooms + " bathroom" 
											 + ",parking of " + this.house.parking + " cars"
											 + " and swimming pool of " + this.house.swimmingPool + " metre square");

		return this.house ;
	}
}


// WE can instead use a Driver class and move this logic to there instaed of writing the code here
public class builder {
	public static void main(String[] args){
		BudgetFriendlyHouse budgetFriendlyHouse = new BudgetFriendlyHouse();
		budgetFriendlyHouse.createRoom(1);
		budgetFriendlyHouse.createKitchen("classic");
		budgetFriendlyHouse.createBathroom(1);
		House smallHouse = budgetFriendlyHouse.build() ;


		budgetFriendlyHouse = new BudgetFriendlyHouse() ;
		budgetFriendlyHouse.createRoom(2) ;
		budgetFriendlyHouse.createKitchen("modern") ;
		budgetFriendlyHouse.createBathroom(2) ;
		House twoBHKHouse = budgetFriendlyHouse.build() ;


		Villa villaObj = new Villa() ;
		villaObj.createRoom(5);
		villaObj.createKitchen("modern");
		villaObj.createBathroom(3);
		villaObj.createParking(2);
		villaObj.createSwimmingPool(5000);
		GrandHouse vipHouse = villaObj.build() ;


		villaObj = new Villa() ;
		villaObj.createRoom(3);
		villaObj.createKitchen("modern");
		villaObj.createBathroom(3);
		villaObj.createParking(1);
		GrandHouse richHouse = villaObj.build() ;
	}
}