
package ar.com.directv.test.spring.integration.async;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OrdersPortService", targetNamespace = "http://www.test.spring.integration.ws/orders", wsdlLocation = "http://localhost:8080/ws/Schemas/processOrderService.wsdl")
public class OrdersPortService
    extends Service
{

    private final static URL ORDERSPORTSERVICE_WSDL_LOCATION;
    private final static WebServiceException ORDERSPORTSERVICE_EXCEPTION;
    private final static QName ORDERSPORTSERVICE_QNAME = new QName("http://www.test.spring.integration.ws/orders", "OrdersPortService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ws/Schemas/processOrderService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ORDERSPORTSERVICE_WSDL_LOCATION = url;
        ORDERSPORTSERVICE_EXCEPTION = e;
    }

    public OrdersPortService() {
        super(__getWsdlLocation(), ORDERSPORTSERVICE_QNAME);
    }

    public OrdersPortService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ORDERSPORTSERVICE_QNAME, features);
    }

    public OrdersPortService(URL wsdlLocation) {
        super(wsdlLocation, ORDERSPORTSERVICE_QNAME);
    }

    public OrdersPortService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ORDERSPORTSERVICE_QNAME, features);
    }

    public OrdersPortService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrdersPortService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OrdersPort
     */
    @WebEndpoint(name = "OrdersPortSoap11")
    public OrdersPort getOrdersPortSoap11() {
        return super.getPort(new QName("http://www.test.spring.integration.ws/orders", "OrdersPortSoap11"), OrdersPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrdersPort
     */
    @WebEndpoint(name = "OrdersPortSoap11")
    public OrdersPort getOrdersPortSoap11(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.test.spring.integration.ws/orders", "OrdersPortSoap11"), OrdersPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ORDERSPORTSERVICE_EXCEPTION!= null) {
            throw ORDERSPORTSERVICE_EXCEPTION;
        }
        return ORDERSPORTSERVICE_WSDL_LOCATION;
    }

}
