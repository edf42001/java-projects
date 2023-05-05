import java.util.HashMap;

public class Equation {
	private HashMap<Integer, Integer> terms;
	public Equation(HashMap<Integer, Integer> terms){
		this.terms = terms;
	}
	
	public Complex calculate(Complex o){
		Complex ans = new Complex();
		for(Integer term: terms.keySet()){
			ans = ans.add(power(o,term).multiply(terms.get(term)));
		}
		return ans;
	}
	
	public static Complex power(Complex o, int power){
		if(power == 0){
			return new Complex(1, 0);
		}else if(power == 1){
			return o;
		}else{
			return o.multiply(power(o, power-1));
		}
	}
	
	public static Complex eToThe(Complex o){
		return new Complex(Math.cos(o.b()), Math.sin(o.b())).multiply(Math.exp(o.a()));
		
	}
}
