package org.freecode.demo;

public class ThreadTest {
	
	public static void main(String[] args)
	{
		System.out.println( "starting main()..." );
		Thread producer = new Thread( new Producer() );
		Thread consumer = new Thread( new Consumer() );
		
		System.out.println( "start Producer..." );
		producer.start();
		
		System.out.println( "start Consumer..." );
		consumer.start();
		
		System.out.println( "interrupt Producer..." );
		producer.interrupt();
		System.out.println( "interrupt Consumer..." );
		consumer.interrupt();
		
		System.out.println("join Producer and Consumer...");
		try
		{
			// Main thread will wait until both threads finish.
		  producer.join();
		  consumer.join();
		}
		catch( InterruptedException ie )
		{
			ie.printStackTrace();
		}
		
		System.out.println( "main() finished." );
	}
	
	

}
