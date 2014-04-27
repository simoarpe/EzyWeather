package com.simon.arpe.ezyweather.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;

public class ConnectionHelper {
	
public static String conntect(URL url){
		
		OkHttpClient client = new OkHttpClient();

	  
	      HttpURLConnection connection = client.open(url);
	      InputStream in = null;
	      try {
	        // Read the response.
	        in = connection.getInputStream();
	        byte[] response = readFully(in);
	        return new String(response, "UTF-8");
	      }
	      catch(UnsupportedEncodingException uee){
	    	  uee.printStackTrace();
	      }
	      
	      catch(IOException ioe){
	    	  ioe.printStackTrace();
	      }
	      
	      finally {
	        if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	      }
		return null;
	  
		
		
	}




	private static byte[] readFully(InputStream in) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = in.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}

}
