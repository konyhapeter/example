package integration2;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.integration.ws.SimpleWebServiceOutboundGateway;
import org.springframework.integration.ws.WebServiceHeaders;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@SpringBootApplication
public class W3SchoolsCelsiusFahrenheitIntegrationApplication {
//	public static void main(String[] args) {
//		ConfigurableApplicationContext ctx = SpringApplication.run(W3SchoolsCelsiusFahrenheitIntegrationApplication.class, args);
//		TempConverter converter = ctx.getBean(TempConverter.class);
//		System.out.println(converter.fahrenheitToCelcius(100.0f));
//
//	}

	@MessagingGateway
	public interface TempConverter {

		@Gateway(requestChannel = "convert.input")
		float fahrenheitToCelcius(float fahren);

	}

	@Bean
	public IntegrationFlow convert() {
		return f -> f
				.transform(payload -> "<FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">" + "<Fahrenheit>"
						+ payload + "</Fahrenheit>" + "</FahrenheitToCelsius>")
				.enrichHeaders(h -> h.header(WebServiceHeaders.SOAP_ACTION,
						"https://www.w3schools.com/xml/FahrenheitToCelsius"))
				.handle(new SimpleWebServiceOutboundGateway("https://www.w3schools.com/xml/tempconvert.asmx"))
				.transform(new GenericTransformer<String, Float>() {

					@Override
					public Float transform(String source) {

						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						InputSource is = new InputSource(new StringReader(source));
						DocumentBuilder dBuilder;
						float result = -1;
						try {
							dBuilder = factory.newDocumentBuilder();
							Document doc = dBuilder.parse(is);
							doc.getDocumentElement().normalize();
							NodeList nodeList = doc.getElementsByTagName("FahrenheitToCelsiusResult");
							for (int i = 0; i < nodeList.getLength(); i++) {

								Node node = nodeList.item(i);
								result = Float.parseFloat(node.getTextContent());
							}
						} catch (ParserConfigurationException | SAXException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return result;
					}
				});
	}
}
