package com.test;
import java.io.BufferedReader;  
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import net.sf.json.JSONObject;  

public class AppHttp {
	    public static String appadd(String ADD_URL,JSONObject obj) {  
	    StringBuffer sb = new StringBuffer(""); 
	     try{   
	            URL url = new URL(ADD_URL);  
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	            connection.setDoOutput(true);  
	            connection.setDoInput(true);  
	            connection.setRequestMethod("POST");  
	            connection.setUseCaches(false);  
	            connection.setInstanceFollowRedirects(true);  
	            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");           
	            connection.connect();  
	            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	            out.write(obj.toString().getBytes("UTF-8"));
	            out.flush();  
	            out.close(); 
	       
	            BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                    connection.getInputStream()));  
	            String lines;
	            while ((lines = reader.readLine()) != null) {  
	                lines = new String(lines.getBytes(), "utf-8");  
	                sb.append(lines);  
	            }  
	            reader.close();  
	            connection.disconnect();  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	     return sb.toString();
	    }  
}


