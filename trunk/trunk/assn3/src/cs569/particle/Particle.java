package cs569.particle;

import javax.vecmath.Vector3f;

public class Particle {
	protected Vector3f pos;
	protected Vector3f velo;
	protected float mass;
	
	protected Vector3f color;
	//protected int age;
	protected float timeBorn; // stored in seconds 
	
	public Particle(Vector3f pos, Vector3f velo, float mass, Vector3f color, float age)
	{
		this.pos = pos;
		this.velo = velo;
		this.mass = mass;
		this.color = color;
		this.timeBorn = age;
	}
	
	public Vector3f getPos()
	{
		return this.pos;
	}
	
	public Vector3f getVelo()
	{
		return this.velo;
	}
	
	public void updatePosVelo(Vector3f pos, Vector3f velo)
	{
		this.pos = pos;
		this.velo = velo;
	}
	
	public void set(Vector3f pos, Vector3f velo, float mass, Vector3f color, float age)
	{
		this.pos = pos;
		this.velo = velo;
		this.mass = mass;
		this.color = color;
		this.timeBorn = age;
	}
	
	public void set(Particle p)
	{
		this.pos = p.pos;
		this.velo = p.velo;
		this.mass = p.mass;
		this.color = p.color;
		this.timeBorn = p.timeBorn;
	}
	
	public Vector3f getColor()
	{
		return this.color;
	}
	
}
