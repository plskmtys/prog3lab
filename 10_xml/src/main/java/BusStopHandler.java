package xfh;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class BusStopHandler extends DefaultHandler{
	
	boolean bname = false;
	String  sname = null;
	boolean bold_name = false;
	String  sold_name = null;
	boolean bwheelchair = false;
	String  swheelchair = null;
	String  shighway = null;
	double lat = 0.0;
	double lon = 0.0;
	double slat = 0.0;
	double slon = 0.0;
	
	String k = "";
	
	private List < BusStop > BSList = null;
    private BusStop bs = null;

    public List < BusStop > getEmpList() 
    {
        return BSList;
    }
    
    public BusStopHandler(double lat1, double lon1)
    {
    	lat=lat1;
    	lon=lon1;
    }
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes att) throws SAXException
	{	
		if (qName.equalsIgnoreCase("node"))
		{	
			String k = null;
			bs = new BusStop();
			if (BSList == null)
	            BSList = new ArrayList < > ();
			k = att.getValue("lat");
			if (k != null)
				slat = Double.parseDouble(k);
			k = att.getValue("lon");
			if (k != null)
				slon = Double.parseDouble(k);
			k = null;
			
		}
		else if (qName.equalsIgnoreCase("tag"))
		{
			k = att.getValue("k");
			if (k != null)
			{
				switch (k)
				{
				case "name":
					sname = att.getValue("v");
					break;
					
				case "old_name":
					sold_name = att.getValue("v");
					break;
					
				case "wheelchair":
					swheelchair = att.getValue("v");
					break;
					
				case "highway":
					shighway = att.getValue("v");
					break;
					
			k = null;
			}
			
		}
//		else if (qName.equalsIgnoreCase("name"))
//		{
//			bname = true;
//		}
//		
//		else if (qName.equalsIgnoreCase("old_name"))
//		{
//			bold_name = true;
//		}
//		
//		else if (qName.equalsIgnoreCase("wheelchair"))
//		{
//			bwheelchair = true;
//		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("node")) 
		{	
			double distance = 0;
			
			if (shighway != null && shighway.equals("bus_stop"))
			{
				bs.setName(sname);
				bs.setWheelchair(swheelchair);
				
				if (sold_name != null)
				{
					bs.setOldName(sold_name);
				}
				
				distance = bs.dist1(lat, lon, slat, slon);
				bs.setdistance(distance);
				BSList.add(bs);
//				System.out.print(bs.toString());
				bs = null;
				shighway = null;
				sold_name = null;
				swheelchair = null;
				sname = null;
			}
			else
			{	
				bs = null;
				shighway = null;
				sold_name = null;
				swheelchair = null;
				sname = null;
			}    
        }
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException
	{
		
		if (bname) 
		{
			System.out.println("name :" +new String(ch, start, length));
			bname = false;
		}
		
		if (bold_name) 
		{
			System.out.println("old_name :" +new String(ch, start, length));
			bold_name = false;
		}
		
		if (bwheelchair) 
		{
			System.out.println("wheelchair :" +new String(ch, start, length));
			bwheelchair = false;
		}
	}
}
