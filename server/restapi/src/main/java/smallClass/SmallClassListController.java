package smallClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SmallClassListController {

    private List<String> users = new ArrayList<String>();

    @RequestMapping(value = "/smallClass/getUsers")
    public List<String> getUsers() {
        return users;
    }

    @RequestMapping(
            value = "/smallClass/addUser",
            method = RequestMethod.POST
    )
    public ResponseEntity< String > addUser(@RequestBody String userName) {
        users.add(userName);
        return new ResponseEntity<>("success" ,HttpStatus.CREATED);
    }
    
}
