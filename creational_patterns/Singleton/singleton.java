import java.util.concurrent.locks.ReentrantLock;


class DBConnection {

	private static DBConnection instance =  null ;
	static ReentrantLock mutex = new ReentrantLock() ; 


	// If I just add all the content of getInstance here, the locking won't be effecting
	// so creating this as private constructor so noone can call this constructor
	private DBConnection(){
		System.out.println("Creating new db object") ;
	}

	// Singleton function ensures that only one object is created at a time
	public static DBConnection open(){
		if (instance == null) {
			// Take lock to start creating the object
			mutex.lock() ; 
			// Ensure that the instance hasn't yet been initialized yet by another thread
			// while this one has been waiting forr the lock's release
			if(instance == null){
				instance = new DBConnection() ;
				mutex.unlock();
				return instance ;
			}
		}
		System.out.println("No new object was created, returning already existing object") ;
		return instance ;
	}
}

class singleton{
	public static void main(String[] args){
		DBConnection.open() ;
		// DBConnection connection2 = new DBConnection() ; // Try to call constructor, this fails while compiling due to private constructor
		DBConnection.open() ;
	}
}