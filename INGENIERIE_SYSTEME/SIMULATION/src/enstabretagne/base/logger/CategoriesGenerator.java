/*
 * 
 */
package enstabretagne.base.logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriesGenerator.
 */
public class CategoriesGenerator {

    /** The segments. */
	List<Segment> segments;
	
	/**
	 * The Class Segment.
	 */
	public class Segment {
		
		/** The low. */
		double low;
		
		/** The high. */
		double high;
		
		/** The index. */
		int index;
		

		
		/**
		 * Instantiates a new segment.
		 *
		 * @param index the index
		 * @param low the low
		 * @param high the high
		 */
		public Segment(int index,double low, double high)
		{
			this.index = index;
			this.low=low;
			this.high=high;
		}
		
		/**
		 * Checks if is in.
		 *
		 * @param i the i
		 * @return true, if is in
		 */
		public boolean isIn(double i){
			return (i<high & i>=low);
		}

        /**
		 * Gets the moy.
		 *
		 * @return the moy
		 */
		public double getMoy()
		{
			return (high+low)/2;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			String moyBound;
			String lowBound;
			String maxBound;
			if(low!=Double.MIN_VALUE){
				lowBound = df.format(low);
				moyBound = df.format(getMoy());
			}
			else {
				lowBound = "-Infini";
				moyBound = lowBound; 
			}
			
			if(high!=Double.MAX_VALUE){
				maxBound = df.format(high);
				moyBound = df.format(getMoy());
			}
			else {
				maxBound = "+Infini";
				moyBound=maxBound;
			}
			String index_s;
			index_s = index_df.format(index);
			
			return index_s+" "+moyBound+" ["+lowBound+";"+maxBound+"[";
		}

    }
	


	/** The df. */
	DecimalFormat df ;
	
	/** The index df. */
	DecimalFormat index_df;
	
	/** The default segment. */
	Segment defaultSegment;


    /**
	 * Instantiates a new categories generator.
	 *
	 * @param borneBasse the borne basse
	 * @param borneHaute the borne haute
	 * @param nbCategories the nb categories
	 * @param minimumintegerDigit the minimuminteger digit
	 * @param minimumFractionDigit the minimum fraction digit
	 */
	public CategoriesGenerator(double borneBasse, double borneHaute, int nbCategories,int minimumintegerDigit,int minimumFractionDigit) {
		super();

        index_df = new DecimalFormat();
		index_df.setMaximumFractionDigits(0);
		index_df.setMinimumFractionDigits(0);
		index_df.setMinimumIntegerDigits(minimumintegerDigit);
		
		df = new DecimalFormat();
		
		if(nbCategories==0)
			nbCategories=1;
		if(nbCategories<0)
			nbCategories = - nbCategories;

        segments = new ArrayList<>();
		
		segments.add(new Segment(0,Double.MIN_VALUE, borneBasse));
		double d = (borneHaute - borneBasse) / nbCategories;
		
		df.setMaximumFractionDigits(minimumFractionDigit);
		df.setMinimumFractionDigits(minimumFractionDigit);
		df.setMinimumIntegerDigits(minimumintegerDigit);
		
		double previousLowBorne;
		previousLowBorne = borneBasse;
		for(int i = 1;i<=nbCategories;i++) {			
			segments.add(new Segment(i,previousLowBorne,previousLowBorne+d));
			previousLowBorne=previousLowBorne+d;
		}
		
		segments.add(new Segment(nbCategories+1,borneHaute,Double.MAX_VALUE));
		
		defaultSegment = new Segment(-1,Double.MIN_VALUE,Double.MAX_VALUE);
	}
	
	/**
	 * Gets the segment of.
	 *
	 * @param i the i
	 * @return the segment of
	 */
	public Segment getSegmentOf(double i){
		for(Segment s : segments)
		{
			if(s.isIn(i))
				return s;			
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res="";
		
		for(Segment s:segments)
			res+=s.toString()+"\n";
		return res;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		CategoriesGenerator cg = new CategoriesGenerator(-3.6, 3.6,10,2,3);
		System.out.println(cg);
		
		System.out.println(cg.getSegmentOf(0.365));
		
	}

}
