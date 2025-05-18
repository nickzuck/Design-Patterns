/*
Problem Statement : Design cookie factory in which you have base dough. Using a cookie cutting method, you can create mulitple cookies of same shape
You can decorate each cookie individually while the original cookie cutter remains unchanged. 
*/

enum Topping {
	CASHEW ("Cashew"),
	CHERRY ("Cherry"),
	ALMONDS ("Almonds"),
	WALNUTS ("Walnuts");

	private final String name ; 

	private Topping(String s) {
		name = s ; 
	}

	public String toString() {
		return this.name ;
	}
};

abstract class CookieCutter {
	private int sides ; 
	private int size ; 
	private Topping toppings ; 


	CookieCutter(int sides, int size, Topping topping){
		this.sides = sides ; 
		this.size = size ; 
		this.toppings = topping;
	}

	// The prototype method which copies cookies to modify it later on
	public CookieCutter clone(CookieCutter source){
		try {
			return (CookieCutter) super.clone() ;	
		} catch (CloneNotSupportedException  e) {
			throw new AssertionError ("Clone method not supported for this type") ;
		}
		
	}


	public void setSide(int sides){
		this.sides = sides ;
	}

	public void setSize(int size) {
		this.size = size ; 
	}

	public void setToppings(Topping topping) {
		this.toppings = topping ;
	}

	public void display() {
		System.out.println("Cookie [Sides=" + sides + ", Size=" + size + ", Topping=" + toppings + "]");
	}
}

class CashewCookie extends CookieCutter {
	CashewCookie(){
		super(0, 5, Topping.CASHEW) ;
	}

	CashewCookie (CashewCookie source){
		super(0, 5, Topping.CASHEW) ;
	}

	public CashewCookie clone() {
		return new CashewCookie(this) ;
	}


}

class AlmondCookie extends CookieCutter {
	AlmondCookie() {
		super(6, 4, Topping.ALMONDS) ;
	}

	AlmondCookie(AlmondCookie source) {
		super(6, 4, Topping.ALMONDS) ;
	}

	public AlmondCookie clone() {
		return new AlmondCookie(this) ;
	}
}

class prototype {
	public static void main(String[] args){
		CashewCookie originalCashewCookie = new CashewCookie() ;
		AlmondCookie originalAlmondCookie = new AlmondCookie() ;
		System.out.print("Original almond cookie: ") ; originalAlmondCookie.display() ;
		System.out.print("Original cashew cookie: ") ; originalCashewCookie.display() ;

		AlmondCookie almondWithWalnutCookie = originalAlmondCookie.clone() ;
		almondWithWalnutCookie.setToppings(Topping.WALNUTS) ;
		System.out.print("Almond walnute cookie: ") ; almondWithWalnutCookie.display();


		CashewCookie cashewCookieWithCherries = originalCashewCookie.clone() ; 
		cashewCookieWithCherries.setToppings(Topping.CHERRY) ;
		System.out.print("Cashew with cherries cookie: ") ; cashewCookieWithCherries.display();

	}
}
