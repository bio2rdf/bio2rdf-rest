package routines;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Author: vincent.emonet@gmail.com
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class BioUtil {

	
	/**
     * Return the accepted Mime type from a html header Accept field
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("accept")
     * 
     * {example} parseContentNegotiation("rdf")
     */
    public static String parseContentNegotiation(String accept) {
    	
    	Pattern p = Pattern.compile("\\s?([^,;]*);?(?:\\s*q=)?([^,]*)?(?:$|,)");
    	Matcher m = p.matcher(accept);
    	double bestQ = 0;
    	double tempQ;
    	int priority = 10;
    	String tempMime = "";
    	String acceptedMime = "";
    	
    	while (m.find())
    	{
    		tempMime = m.group(1);
    		if (m.group(1).equals("text/n3") || m.group(1).equals("application/rdf+n3") || m.group(1).equals("application/n3") || m.group(1).equals("text/*"))
    		{
    			tempMime = "text/rdf+n3";
    		}
    		else if (m.group(1).equals("*/*") || m.group(1).equals("application/*"))
    		{
    			tempMime = "application/rdf+xml";
    		}
    		
    		if (m.group(2).equals(""))
    		{
    			tempQ = 1.0;
    		}
    		else
    		{
    			tempQ = Double.parseDouble(m.group(2));
    		}
    		
    		if (tempQ > bestQ && BioUtil.getMimePriority(tempMime) != -1)
    		{
    			acceptedMime = tempMime;
    			priority = BioUtil.getMimePriority(tempMime);
    			bestQ = tempQ;
    		}
    		else if (tempQ == bestQ && BioUtil.getMimePriority(tempMime) < priority && BioUtil.getMimePriority(tempMime) != -1)
    		{
    			acceptedMime = tempMime;
    			priority = BioUtil.getMimePriority(tempMime);
    		}
    		    		
    	}    	
    	
		return(acceptedMime);
    }



/**
 * Return the priority (0 is the preferred, -1 is not supported) of the rdf MIME type for the REST content negotiation
 * 
 * 
 * {talendTypes} String
 * 
 * {Category} User Defined
 * 
 * {param} string("mimeString")
 * 
 * {example} getAcceptableMime("application/rdf+xml")
 */
public static int getMimePriority(String mimeString) {
	List<String> acceptableMime = new ArrayList<String>();
	
	acceptableMime.add("application/rdf+xml");
	acceptableMime.add("text/rdf+n3");
	acceptableMime.add("text/turtle");
	acceptableMime.add("text/plain");
	acceptableMime.add("text/html");
	acceptableMime.add("application/x-json+ld");
	acceptableMime.add("application/json");
	acceptableMime.add("application/ld+json");
	acceptableMime.add("application/trig");
	acceptableMime.add("application/x-trig");
	acceptableMime.add("application/rdf+json");

	// Add mime type here if you want to parse them
	
	return(acceptableMime.indexOf(mimeString));
}


	
	
    /**
     * Return well-written method for Bio2RDF REST service
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("methodIn")
     * 
     * {example} restMethod("linksns")
     */
    public static String restMethod(String methodIn) {
    	if (methodIn.toLowerCase().equals("describe"))
    	{
    		return("describe");
    	}
    	else if (methodIn.toLowerCase().equals("links") || methodIn.toLowerCase().equals("linksns"))
    	{
    		return("links");
    	}
    	else if (methodIn.toLowerCase().equals("search") || methodIn.toLowerCase().equals("searchns"))
    	{
    		return("search");
    	}
    	else if (methodIn.toLowerCase().equals("rest_describe") || methodIn.toLowerCase().equals("describe_rest"))
    	{
    		return("rest_describe");
    	}
    	else if (methodIn.toLowerCase().equals("rest_search") || methodIn.toLowerCase().equals("search_rest"))
    	{
    		return("rest_search");
    	}
    	else if (methodIn.toLowerCase().equals("rest_links") || methodIn.toLowerCase().equals("links_rest"))
    	{
    		return("rest_links");
    	}
    	else
    	{
    		return(methodIn.toLowerCase());
    	}
    }
	
    
    /**
     * Return well-written format for Bio2RDF REST service
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("format")
     * 
     * {example} restFormat("rdf")
     */
    public static String getFormatFromMime(String formatIn) {
    	if (formatIn.toLowerCase().equals("rdf") || formatIn.toLowerCase().equals("rdfxml") || formatIn.toLowerCase().equals("application/rdf+xml"))
    	{
    		return("rdfxml");
    	}
    	else if (formatIn.toLowerCase().equals("n3") || formatIn.toLowerCase().equals("text/rdf+n3")) 
    	{
    		return("n3");
    	}
    	else if (formatIn.toLowerCase().equals("ttl") || formatIn.toLowerCase().equals("turtle") || formatIn.toLowerCase().equals("text/turtle")) 
    	{
    		return("turtle");
    	}
    	else if (formatIn.toLowerCase().equals("nt")  || formatIn.toLowerCase().equals("text/plain")  || formatIn.toLowerCase().equals("ntriple")  || formatIn.toLowerCase().equals("ntriples")) 
    	{
    		return("ntriple");
    	}
    	else if (formatIn.toLowerCase().equals("json")  || formatIn.toLowerCase().equals("application/rdf+json") || formatIn.toLowerCase().equals("rdfjson")) 
    	{
    		return("rdfjson");
    	}
    	else if (formatIn.toLowerCase().equals("jsonld")  || formatIn.toLowerCase().equals("application/x-json+ld") || formatIn.toLowerCase().equals("application/ld+json") || formatIn.toLowerCase().equals("application/json") || formatIn.toLowerCase().equals("ldjson") )
    	{
    		return("jsonld");
    	}
    	else if (formatIn.toLowerCase().equals("trig") || formatIn.toLowerCase().equals("application/x-trig") || formatIn.toLowerCase().equals("application/trig")) 
    	{
    		return("trig");
    	}
    	else
    	{
    		System.err.println("Incompatible format");
    		return("null");
    	}
    }
    
	
    /**
     * Return well-written format for Bio2RDF REST service
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("format")
     * 
     * {example} restFormat("rdf")
     */
    public static String getMimeFromFormat(String formatIn) {
    	if (formatIn.toLowerCase().equals("rdf") || formatIn.toLowerCase().equals("rdfxml"))
    	{
    		return("application/rdf+xml");
    	}
    	else if (formatIn.toLowerCase().equals("n3")) 
    	{
    		return("text/rdf+n3");
    	}
    	else if (formatIn.toLowerCase().equals("ttl") || formatIn.toLowerCase().equals("turtle")) 
    	{
    		return("text/turtle");
    	}
    	else if (formatIn.toLowerCase().equals("nt") ) 
    	{
    		return("text/plain");
    	}
    	else if (formatIn.toLowerCase().equals("json") ) 
    	{
    		return("application/json");
    	}
    	else if (formatIn.toLowerCase().equals("jsonld") )
    	{
    		return("application/ld+json");
    	}
    	else if (formatIn.toLowerCase().equals("trig") )
    	{
    		return("application/trig");
    	}
    	else
    	{
    		return("null");
    	}
    }
    

    /**
     * Return well-written format for Jena library use
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("format")
     * 
     * {example} jenaFormat("rdf")
     */
    public static String jenaFormat(String formatIn) {
    	if (formatIn.toLowerCase().equals("rdf") || formatIn.toLowerCase().equals("rdf/xml") || formatIn.toLowerCase().equals("xml") || formatIn.toLowerCase().equals("application/rdf+xml"))
    	{
    		return("RDF/XML");
    	}
    	else if (formatIn.toLowerCase().equals("turtle") || formatIn.toLowerCase().equals("ttl") || formatIn.toLowerCase().equals("text/turtle"))
    	{
    		return("TURTLE");
    	}
    	else if (formatIn.toLowerCase().equals("nt") || formatIn.toLowerCase().equals("n-triple") || formatIn.toLowerCase().equals("text/plain")) 
    	{
    		return("N-TRIPLE");
    	}
    	else if (formatIn.toLowerCase().equals("n3") || formatIn.toLowerCase().equals("text/rdf+n3")) 
    	{
    		return("N3");
    	}
    	else if (formatIn.toLowerCase().equals("rdf/json") || formatIn.toLowerCase().equals("json") || formatIn.toLowerCase().equals("application/json"))
    	{
    		return("RDF/JSON");
    	}
    	else
    	{
    		return("null");
    	}
    }
    
    /**
     * Return "" if the string is null
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} string("str")
     * 
     * {example} nullCheck("test")
     */
    public static String nullCheck(String str) {
    	if (str == null)
    	{
    		return("");
    	}
    	else
    	{
    		return(str);
    	}
    }
    
    
}
