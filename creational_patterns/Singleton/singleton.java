import java.util.concurrent.locks.ReentrantLock;


class DBConnection {

	private static boolean instanceCreated =  false ;
	static ReentrantLock mutex = new ReentrantLock() ; 


	// If I just add all the content of getInstance here, the locking won't be effecting
	// so creating this as private constructor so noone can call this constructor
	private DBConnection(){
		System.out.println("Trying to create the db object") ;
	}

	// Singleton function ensures that only one object is created at a time
	public static void open(){
		if (!instanceCreated) {
			// Take lock to start creating the object
			mutex.lock() ; 
			// Ensure that the instance hasn't yet been initialized yet by another thread
			// while this one has been waiting forr the lock's release
			if(!instanceCreated){
				System.out.println("Opening connection for database with username and password") ;
				instanceCreated = true ;
				mutex.unlock();
			}
		} else {
			System.out.println("No new connection was created") ;
		}
	}
}

class singleton{
	public static void main(String[] args){
		DBConnection.open() ;
		// DBConnection connection2 = new DBConnection() ; // Try to call constructor, this fails while compiling due to private constructor
		DBConnection.open() ;
	}
}