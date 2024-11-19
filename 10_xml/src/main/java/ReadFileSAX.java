package xfh;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ReadFileSAX {
	
	public static void main(String [] args)
	{	
		
		String fname = "bme.xml";
		double lat = 0.0;
		double lon = 0.0;
		
		for (int i = 0; i < args.length; i++)
		{
			if(args[i].equals("-i"))
				fname = args[i+1];
			else if (args[i].equals("-lat"))
				lat = Double.parseDouble(args[i+1]);
			else if (args[i].equals("-lon"))
				lat = Double.parseDouble(args[i+1]);
		}
	
		SAXParserFactory fy = SAXParserFactory.newInstance();
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
			
		try 
		{
			
			SAXParser sxp = fy.newSAXParser();
			BusStopHandler handler = new BusStopHandler(lat,lon);
			File f = new File(fname);
			sxp.parse(f, handler);
			int k = 0;

			List < BusStop > BSList = handler.getEmpList();
			List < BusStop > BSList2 = BSList;
			
			while (BSList2.size() != 0)
			{
				for (int i = 0; i < BSList.size(); i++) {
					  if (BSList2.get(i).getdistance() < BSList2.get(k).getdistance())
						  k = i;
			}
				System.out.println(BSList2.get(k).toString());
				BSList2.remove(k);
				k = 0;
			}
		} 
		catch (ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
