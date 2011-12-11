package org.javatrainer;

public class Calc {
	 private Double a;
	    private Double b;
	    private Double c;

	    /** Creates a new instance of KCalc */
	    public Calc() 
	    {
	    }

	    public Double getA() 
	    {
	        return a;
	    }

	    public void setA(Double a) 
	    {
	        this.a = a;
	    }
	    
	    public Double getB() 
	    {
	        return b;
	    }

	    public void setB(Double b) 
	    {
	        this.b = b;
	    }

	    public Double getC() 
	    {
	        return c = a + b;
	    }

	    public void setC(Double c)
	    {
	        this.c = c;
	    }
}
