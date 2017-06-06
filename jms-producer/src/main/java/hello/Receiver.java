package hello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    //@JmsListener(destination = "processEmpChannel", containerFactory = "myFactory")
    public void receiveMessage(Order emp) {
        System.out.println("Received <" + emp + ">");
    }

}
