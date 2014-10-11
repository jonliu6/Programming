package org.freecode.demo;

public class Consumer implements Runnable {

    private boolean shouldBeStopped = false;
	
	public void stopMe()
	{
		shouldBeStopped = true;
	}
	
	public void run()
	{
		if ( ! shouldBeStopped )
		{
			for ( int i = 0; i < 10; i++ )
			{
				System.out.println( "Consumer received " + Integer.valueOf(i + 1) );
			}
		}
	}
}
