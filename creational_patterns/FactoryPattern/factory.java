/*
Problem Statement : Create a logistics app which can transport goods. For starters, the goods can be
transported through Sea and Land transportation. Make sure to write the code in a flexible way so
that it can open to extend for more transportation in the future. 
*/


interface Transport {
	public void deliver() ;
	public void book() ;
}


class Ship implements Transport {
	public void deliver(){
		System.out.println("Item delivered by ship\n") ;
	}

	public void book(){
		System.out.println("A ship is booked") ;
	}
}

class Truck implements Transport {

	public void deliver(){
		System.out.println("Item delivered by truck\n");
	}

	public void book(){
		System.out.println("A truck is booked") ;
	}

}


abstract class Logistic {
	protected Transport obj;

	public abstract Transport createTransport() ; 
	public void shipItems(){
		obj.deliver() ;
	}
}

class OceanLogistics extends Logistic {
	
	@Override
	public Transport createTransport(){
		this.obj = new Ship() ;
		this.obj.book() ;
		return obj ; 
	}
}

class RoadLogistics extends Logistic {

	@Override
	public Transport createTransport(){
		this.obj = new Truck() ;
		this.obj.book() ;
		return obj; 
	}

}

public class factory {
	public static void main(String[] args){
		Logistic logistic ;

		// run for road 
		logistic = new RoadLogistics() ;
		logistic.createTransport() ;
		logistic.shipItems() ;


		// run for ocean
		logistic = new OceanLogistics() ;
		logistic.createTransport();
		logistic.shipItems();

		// can also be extended to use Air as a means
	}
}

