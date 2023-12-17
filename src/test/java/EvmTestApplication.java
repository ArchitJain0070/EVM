import com.assignment.evm.controller.EVMController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvmTestApplication {

    @Test
    public void testEnterCandidate(){
        EVMController evmController = new EVMController();
        evmController.registerCandidate("Archit");
    }
    @Test
    public void testCastVote(){
        EVMController evmController = new EVMController();
        evmController.registerCandidate("Archit");
        evmController.castVote("Archit");
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Vote Count",1);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(result),evmController.countVote("Archit"));
    }
    @Test
    public void testCountVote(){
        EVMController evmController = new EVMController();
        evmController.registerCandidate("Archit");
        evmController.castVote("Archit");
        evmController.castVote("Archit");
        evmController.countVote("Archit");
        HashMap<String,Integer> result = new HashMap<>();
        result.put("Vote Count",2);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(result),evmController.countVote("Archit"));
    }
    @Test
    public void testListVote(){
        EVMController evmController = new EVMController();
        evmController.registerCandidate("Archit");
        evmController.registerCandidate("Naveen");
        evmController.castVote("Archit");
        evmController.castVote("Archit");
        evmController.castVote("Naveen");
        HashMap<String,Object> result = new HashMap<>();
        ArrayList< HashMap<String,Object>>array = new ArrayList<>();
        HashMap<String,Object>secondCandidate = new HashMap<>();
        result.put("Votes",2);
        result.put("Name","Archit");
        array.add(result);
        secondCandidate.put("Votes",1);
        secondCandidate.put("Name","Naveen");
        array.add(secondCandidate);
        HashMap<String, ArrayList<HashMap<String, Object>>> finalResponse = new HashMap<>();
        finalResponse.put("Vote List",array);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(finalResponse),evmController.listVote());
    }

    @Test
    public void test(){
        EVMController evmController = new EVMController();
        evmController.registerCandidate("Archit");
        evmController.registerCandidate("Naveen");
        evmController.castVote("Archit");
        evmController.castVote("Archit");
        evmController.castVote("Naveen");
        evmController.getWinner();
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Winner is Archit with number of Votes 2"),evmController.getWinner());
    }

}
