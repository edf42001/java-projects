import java.awt.Color;

public class Complex {
	private double a;
	private double b;
	
	public Complex(){
		this.a = 0;
		this.b = 0;
	}
	
	public Complex(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	public Complex add(Complex o){
		return new Complex(this.a+o.a, this.b+o.b);
	}
	
	public Complex minus(Complex o){
		return new Complex(this.a-o.a, this.b-o.b);
	}
	
	public Complex multiply(Complex o){
		return new Complex(a*o.a-b*o.b, a*o.b+b*o.a);
	}
	
	public Complex multiply(double k){
		return new Complex(k*a, k*b);
	}
	
	public Color getColor(){
		float hue = (float) (Math.atan2(b, a)/2/Math.PI);
		hue = hue < 0 && hue>-3E-8?0:hue; 	//Fix weird bug where hues in the range -3E-8 - -1E-* give black???
		return Color.getHSBColor(hue, 1f, (float) (1-0.3/(0.3+magnitude())));
	}
	
	public double magnitude(){
		return Math.sqrt(a*a+b*b);
	}
	
	public double b(){
		return b;
	}
	
	public double a(){
		return a;
	}
	
	public String toString(){
		return a + " + " + b + "i";
	}
}
