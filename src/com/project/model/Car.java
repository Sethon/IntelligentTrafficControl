public class Car
{
	
	int MinSpeed = 0;
	int MaxSpeed = 5;
	
	public final Trajectory trajectory;
	
	public Car(Trajectory traj){
		trajectory = traj;
	}
	
	public int Acceleration(int actualV)			// +1 to each car's velocity but can not go up the Maxspeed
	{
		actualV = Math.min(actualV, MaxSpeed);
		
		return actualV;
	}
	
	public int SlowingDown (int actualV, int gap) // if the gap between 2 cars is smaller than the ActualV of the car behind 
													//then the car have to slowdown for not having a collision
	{
		if(actualV < gap)
		{
			actualV = gap;
		}
		
		return actualV;
	}
	
	public int ProbOfSlowingDown(int actualV, double probability) // inserting a probablity of each car to slow down by one V's unit 
	{
		if( (actualV > 0 ) && ( Math.random() <= probability) )
		{
			actualV--;
		}	
		
		return actualV;
	}
	


}