package ar.com.directv.test.spring.integration.nio;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

public class MyJaxb2RootElementHttpMessageConverter extends Jaxb2RootElementHttpMessageConverter {

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	protected void writeToResult(Object o, HttpHeaders headers, Result result) throws IOException {

		StringResult tmp = new StringResult(); 
		
		StreamResult rs = (StreamResult) result;
		
		String r = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ord=\"http://www.test.spring.integration.ws/orders\">\n" + 
		"   <soapenv:Header/>\n" + 
		"   <soapenv:Body>\n";
		rs.getOutputStream().write(r.getBytes());
		 
		 
		
		
		super.writeToResult(o, headers, tmp);
		
		r = tmp.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "") 
				+	"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		rs.getOutputStream().write(r.getBytes());
		
	}

	@Override
	protected Object readFromSource(Class<?> clazz, HttpHeaders headers, Source source) throws IOException {
		
		try {  
		StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.transform(source,result);
	    String str = writer.toString();
		
		str = str.replace("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body>", "");
		str = str.replace("</SOAP-ENV:Body></SOAP-ENV:Envelope>", ""); 
				StringSource s = new StringSource(str);
		
		return super.readFromSource(clazz, headers, s);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	

}
