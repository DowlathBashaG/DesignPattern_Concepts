# DesignPattern_Concepts



Design Patterns :
---------------

Creational Design Patterns :
--------------------------

1. Builder Design Pattern :
-------------------------

   * We have a complex process to contruct an object involving multiple steps, then builder design pattern can help us.
  
   * In Builder we have the logic related to object construction from "client" code & abstract it in separate classes.
   

   a. What problem builder design pattern solves?
   
		Class constructor requires a lot of information.
   
		// Product instances are immutable
		
		class Product{
		
		   public Product(int weight,double price, int shipVolume, int shipCode){
		   
		   // initialize
		   
		   }
		   
		   // other code
		}
		
	    Objects that need other objects or "parts" to construct them.
		
		class Address{
		    public Address(String houseNumber, String street....){
			//initialize
			}
		}
		
		class User{
		    public User(String name,Addres address,LocalDate birthDate,List<Role> roles){
			// initialize
			}
			//other code
		}
		
	
	Implement a Builder :
	
	*  We start by creating a builder
	
	   - Identify the "parts" of the product & provide methods to create those parts.
	   - It should provide a method to "assemble" or build the product/object
	   - It must provide a way / method to get fully built object out. Optionally builder can keep reference to an product it has build so the same 
	     can be returned  again in future.
		 
	*  A director can be a separate class or client can play the role of directory.
	
	Implementation Considerations :
	
	* You can easily create an immutable class by implementing builder as an inner static class. You will find this type of implementation used quite frequently 
	  even if immutability is not a main concern.
	  
	Example :
	-------
	
	User.java :
	---------
	
	public class User{
	
		private final String firstName;
		private final String lastName;
		private final int age;
		private final String phoneNumber;
		private final String address;
		
	public User(UserBuilder builder){
	
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
	}
	
	@Override
	public String toString(){
	return "Name: "+this.firstName+" "+ this.lastName + "\n" + "Age: "+ this.age +" Phone: "+this.phoneNumber+"Address: "+ this.address;
	}
	
	public static class UserBuilder{
	
		private final String firstName;
		private final String lastName;
		private  int age;
		private  String phoneNumber;
		private  String address;
	
	    public UserBuilder(String firstName, String lastName){
			this.firstName = firstName;
			this.lastName = lastName;
	    }
		
		public UserBuilder age(int age){
			this.age = age;
			return this;
    	}
	   
	    public UserBuilder phoneNumber(String phoneNumber){
			this.phoneNumber = phoneNumber;
			return this;
	    }
		
		public UserBuilder address(String address){
		    this.address = address;
			return this;
		}
		
		public User build(){
			return new User(this);
		}
	}

	}
	
	Client (Main):
	-------------
	
	Main.java:
	---------
	
	public class Main{

    public static void main(String args[]){

        User James = new User.UserBuilder("Jame","Bond")
                .address("123,London")
                .age(45)
                .phoneNumber("007")
                .build();

        System.out.println(James);

    }
    }
	
	Examples of a Builder Pattern :
	
	* The java.lang.StringBuilder class as well as various buffer classes in java.nio. pkg lie ByteBuffer, CharBuffer are often given as examples of builder pattern.
	
	* There is another great example of builder pattern in java 8. The java.util.Calendar.Builder class is a builder.
	
	
	Compare & Contrast with Prototype:
	
	
	Builder 																				Prototype
	
	* We have complex constructor and builder allows us to work with that.					Prototype allows to altogether skip using constructor.
	
	* We can create a builder as separate class and so it can work with 					In java this pattern works using clone method, and needs to modify
	  legacy code.																			existing code so may not work with legacy code.
	  
	  
	Pitfalls.....
	
	* A little bit complex for new comers mainly because of "method chaining" where builder methods return builder object itself.
	
2. Prototype Design Pattern :
   ------------------------

       - We have a complex object that is costly to create. To create more instances of such class, we use an existing instance as our prototype.
       
       - Prototype will allow us to make copies of existing object & save us from having to recreate objects from scratch.
       
       - Object creation is expensive.
       
       - Used when creating an instance of a given class is either expensive or complicated.
       
   Implement a Prototype :
   ----------------------
   
       * We start by creating a class which will be a prototype.
       
         - The calss must implement Cloneable interface.
    	 
    	 - Class should override clone method and return copy of itself.
    	 
    	 - The method should declare CloneNotSupportedException in throws clause to give subclasses chance to decide on whether or support cloning.
    	 
    	 - Clone method implementation should consider the deep & shallow copy and choose whichever is applicable.
       
    
    	Animal (I) :
       
        package io.dowlath.designpattern.creational.prototype.solution;
    
    		public interface Animal extends Cloneable {
    			Animal clone();
    	}

    Person (c) :
	
    	package io.dowlath.designpattern.creational.prototype.solution;
    
        public class Person implements Animal{
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        private String name;
        private int age;
    
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            System.out.println("Person is created");
        }
    
        @Override
        public Animal clone(){
            System.out.println("Creating Person...");
            Person person = null;
            try {
                person = (Person) super.clone();


        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

        @Override
        public String toString(){
            return "Person: " + name;
        }
        }
	
	Dolphin (c) :
	
    	package io.dowlath.designpattern.creational.prototype;
    
        public class Dolphin implements Prototype{
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public int getAge() {
            return age;
        }
    
        public void setAge(int age) {
            this.age = age;
        }
    
        private String name;
        private int age;
    
        public Dolphin(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        @Override
        public Prototype clone() {
            return new Dolphin(name,age);
        }
    
        @Override
        public String toString(){
            return "This is person named : "+name;
        }
        }
	
	Main (c) :
	
    	package io.dowlath.designpattern.creational.prototype.solution;
    
        public class Main {
        public static void main(String[] args) {
            Person person = new Person("Dowlath",45);
            System.out.println("Person 1: "+ person);
    
            Person secondPerson = (Person) person.clone();
            System.out.println("Person copy...: "+ secondPerson);
    
            System.out.println(System.identityHashCode(person) + "\n" + System.identityHashCode(secondPerson));
        }
        }

3. Singleton Design Pattern:
---------------------------
	
	A. 	EagerSingleton :
	-------------------
	
	public class EagerSingleton{
	
	    //1. Define the constructor as private.
		private EagerSingleton(){
		}
		
		//2. Declare the instance
		private static final EagerSingleton instance = new EagerSingleton();
		
		//3. static factory method
		public static EagerSingleton getInstance(){
		   return instance;
		}
    }

     
	Main / Client class :
	--------------------
	
	public class Main{
	public static void main(String args[]){
	System.out.println("Hello world...");
	
	EagerSingleton obj1 = EagerSingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	EagerSingleton obj2 = EagerSingleton.getInstance();
	System.out.println(obj2.hashCode());
	
	}
	}
	
	Note:
	
	hashcode is same , it means it is singleton class, and it is not recommented.
	
	
	B. LazySingleton :
	-----------------
	
	public class LazySingleton{
	
	    //1. Define the constructor as private.
		private LazySingleton(){
		}
		
		//2. Declare the instance
		private static LazySingleton instance;
		
		//3. static factory method
		public static LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton();
		}
        }else {
		return instance;
		
		}
    }

    Main / Client class :
	--------------------
	
	public class Main{
	public static void main(String args[]){
	System.out.println("Hello world...");
	
	LazySingleton obj1 = LazySingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	LazySingleton obj2 = LazySingleton.getInstance();
	System.out.println(obj2.hashCode());
	
	}
	}
    
	Note:
	
	hashcode is same , it means it is singleton class, and it is not recommented. It wont work on multhread environment.so use synchronized keyword.
     	
	C. LazySingleton with Synchronized keyword 
	------------------------------------------
	
	public class LazySingleton{
	
	    //1. Define the constructor as private.
		private LazySingleton(){
		}
		
		//2. Declare the instance
		private static LazySingleton instance;
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); //2 object by 2 thread...it leads the performance issue.to avoid use double check locking.
		} 
		}else{
		return instance;
		}
		
    }
	
	D. DoubleCheckLazySingleton with Synchronized keyword :
	-----------------------------------------------------
	
	public class DoubleCheckLazySingleton{
	
	    //1. Define the constructor as private.
		private DoubleCheckLazySingleton(){
		}
		
		//2. Declare the instance
		private static DoubleCheckLazySingleton instance;
		
		//3. static factory method
		public static DoubleCheckLazySingleton getInstance(){
		if(instance==null){
			synchronized(DoubleCheckLazySingleton.class){
				if(instance==null){
				instance = new DoubleCheckLazySingleton(); 
					}
				}
			}        
			return instance;
		}
		
    }
	
	Main / Client class :
	--------------------
	
	public class Main{
	public static void main(String args[]){
	System.out.println("Hello world...");
	
	DoubleCheckLazySingleton obj1 = DoubleCheckLazySingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	DoubleCheckLazySingleton obj2 = DoubleCheckLazySingleton.getInstance();
	System.out.println(obj2.hashCode());
	
	}
	}
		
	Note:
	-----
	
	hashcode is same , it means it is singleton class, and it is recommented. 
		

    E. LazyInnerClassSingleton
	--------------------------
	
    public class LazyInnerClassSingleton{
	
	    //1. Define the constructor as private.
		private LazyInnerClassSingleton(){
		}
		
		//2. Create singleton helper class
		public static class SingletonHelper{
		private static final LazyInnerClassSingleton instance = new LazyInnerClassSingleton();
		}
		
		//3.Other class access globally.
		public static LazyInnerClassSingleton getInstance(){
		return SingletonHelper.instance;
		}
		
    }	

    Main / Client class :
	--------------------
	
	public class Main{
	public static void main(String args[]){
	System.out.println("Hello world...");
	
	LazyInnerClassSingleton obj1 = LazyInnerClassSingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	LazyInnerClassSingleton obj2 = LazyInnerClassSingleton.getInstance();
	System.out.println(obj2.hashCode());
	
	}
	}
	
	Note:
	-----
	
	hashcode is same , it means it is singleton class. It is thread safe. And it is recommented.
	
	
	Different way to break the singleton class.
	
	1. Reflection
	2. Serialize / Deserialize Objects
	3. Clone 
	
	F. ENUM based singleton :
	------------------------
	
	public enum Singleton{
	
	   INSTANCE;
	   
	   public void perform(){
	   System.out.println("Doing some task...!!!"+this.hashCode());
	   }
	}
	
	Main singleton class:
	--------------------------------------
	
	public class Test{
	public static void main(String args[]){
	  
	  Singleton singleton1 = Singleton.INSTANCE;
	  singleton1.perform();
	  
	  Singleton singleton2 = Singleton.INSTANCE;
	  singleton2.perform();
	  
	  System.out.println(singleton1.hashCode() == singleton2.hashCode());
	  
	  
	  }
	}
	
	
	Result :
	
	true
	
	Break ENUM singleton class :
	----------------------------
	
	public class ReflectionTest{
	public static void main(String args[]){
	Singleton singleton1 = Singleton.INSTANCE;
	try{
		Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
		for(Constructor<?> constructor:constructors){
				constructor.setAccessible(true); // breaking
				Singleton singleton2 = (Singleton) constructor.newInstance();
				singleton2.perform();
		}
	} catch(Exception e){
	System.out.println(e.getMessage());
	}
	
	singleton1.perform();
	}
	}
	
	Note: Cannot break.
	
	
	How to prevent Singleton from Reflection , Serialize and Clone.?
	---------------------------------------------------------------
	
	A. Clone :
	---------
	
	
	1. MyClone :
	
	   public class MyClone implements Cloneable{
	   
	   @Override
	   protected Object clone() throws CloneNotSupportedException{
	   return super.clone();
	   }
	   }
	
	
	2. LazySingleton :
	
		public class LazySingleton extends MyClone{
	
	    //1. Define the constructor as private.
		private LazySingleton(){
		}
		
		//2. Declare the instance
		private static LazySingleton instance;
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); 
		} 
		}else{
		return instance;
		}
		
    }
	
	
	3. Main :
	
	public class Main{
	public static void main(String args[]) throws CloneNotSupportedException{
	
	
	LazySingleton obj1 = LazySingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	LazySingleton obj2 = (LazySingleton)obj1.clone();
	System.out.println(obj2.hashCode());
	
	}
	}
		
	Note :
	
	Getting 2 different hash codes.
	
	Now we need to prevent from Clone : 
	----------------------------------
	
	public class LazySingleton extends MyClone{
	
	    //1. Define the constructor as private.
		private LazySingleton(){
		}
		
		//2. Declare the instance
		private static LazySingleton instance;
		
		@Override
		protected Object clone() throws CloneNotSupportedException {                             <------------ important step for breaking
		throw new CloneNotSupportedException();
		}
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); 
		} 
		}else{
		return instance;
		}
		
    }
	
	Output: While executing Main class , you can see this exception. Only one instance is creating second is not creating and it is not allowed here.
	
	java.lang.CloneNotSupportedException. 
	
	
	B. Reflection :
	
	public class Main{
	public static void main(String args[]) throws CloneNotSupportedException , InstantiationException, IllegalAccessException, IllegalArgumentException{
	
	
	LazySingleton instance1 = LazySingleton.getInstance();
	System.out.println(obj1.hashCode());
	
	LazySingleton reflectionInstance = null;
	
		Constructor<?>[] constructors = LazySingleton.class.getDeclaredConstructors();            <---------- important step for breaking
		for(Constructor<?> constructor:constructors){ 
				constructor.setAccessible(true); // breaking
				reflectionInstance = (LazySingleton) constructor.newInstance();
		}	
	
	System.out.println(reflectionInstance.hashCode());
	
	}
	}
	
	Output :  Using reflection  creating two different hashcodes.
	
	How to fix this :
	
	public class LazySingleton extends MyClone{
	
	    private static LazySingleton instance;
			
	    //1. Define the constructor as private.
		private LazySingleton(){
			if(instance != null){                                                              <------------ important step for fixing reflection break
			throw new IllegalStateException("object can't be create using reflection");
			}
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {                             
		throw new CloneNotSupportedException();
		}
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); 
		} 
		}else{
		return instance;
		}
		
    }
	
	Output : run the application and check now it wont allow creat two hashcodes are different.
	
	Getting exception :
	
	java.lang.reflect.InvocationTargetException......
	
	object can't be create using reflection
	
	
	C. Serialization :
	-----------------
	
	
	public class LazySingleton extends MyClone implements Serializable{
	
	    private static LazySingleton instance;
			
	    //1. Define the constructor as private.
		private LazySingleton(){
			if(instance != null){                                                             
			throw new IllegalStateException("object can't be create using reflection");
			}
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {                             
		throw new CloneNotSupportedException();
		}
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); 
		} 
		}else{
		return instance;
		}
		
    }
	
	Main :
	
	public class Main{
	public static void main(String args[]) throws CloneNotSupportedException , InstantiationException, IllegalAccessException, IllegalArgumentException,
	FileNotFoundException, IOException ,InvocationTargetException{
	
	
	LazySingleton instance1 = LazySingleton.getInstance();
	System.out.println(instance1.hashCode());
	
	// Serialzie singleton object to a file.
	ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
	out.writeObject(instance1);
	out.close();
	
	//Deserialize singleton object from the file
	ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.ser"));
	LazySingleton instance2 = (LazySingleton)in.readObject();
	in.close();
	
	
	System.out.println("instance1 hashcode :"+ instnace1.hashCode());
	System.out.println("instance2 hashcode :"+ instnace2.hashCode());
	
	}
	}
	
	Output:
	
	if you run this application , now you can get 2 different hashcodes. (2 different objects).
	
	How to fix this issue here.
	---------------------------
	
	public class LazySingleton extends MyClone implements Serializable{
	
	    private static LazySingleton instance;
			
	    //1. Define the constructor as private.
		private LazySingleton(){
			if(instance != null){                                                             
			throw new IllegalStateException("object can't be create using reflection");
			}
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {                             
		throw new CloneNotSupportedException();
		}
		
		protected Object readResolve(){                                                <----------- fix here for serialization break.
		 return instance;
		}
		
		//3. static factory method
		public static synchronized LazySingleton getInstance(){
		if(instance==null){
		   return instance = new LazySingleton(); 
		} 
		}else{
		return instance;
		}
		
    }
	
	
	Note : 
	
	Now run this class, now unable to create new instance. Both hashcode are same.
	
	
4. Factory Method Design Pattern :
-------------------------------
   
		# We want to move the object creation logic from our code to a separate class.
		
		# Factory method pattern is "subclasses providing the actual instance".
		
		# We use this pattern when we dont know in advance which class we may need to instantiate beforehand & also to allow new classes to be added to system and handle their creation with out affecting client code.
		
		# We let subclasses decide which object to instantiate by overriding the factory method.

        Examples of a factory method :
		------------------------------
		
		* The java.util.Collection has an abstract method called iterator(). This method is an example of a factory method.
		
		Pitfalls :
		--------
		
		* More complex to implement. More classes involved and need unit testing.
		
		* Sometimes this pattern forces you to subclass just to create appropriate instance.
		
