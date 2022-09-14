package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/helloWithName")
    public String ahojSeJmenem( @RequestParam(value = "jmeno", required = false) String jmeno) {
        if (jmeno == null) {
            return "Ahoj, škoda že nevím tvoje jméno";
        }
        return "Ahoj " + jmeno + " !";
    }

    Map<Long, Student> studentMap = new HashMap<>();

    // Pomocná proměnná k uchování pořadí v sekvenci u mapy studentů
    Long seq = 0L;


    // Přidání studenta do mapy (náhodné id) ---- POST   ()
    @PostMapping("/students")
    public Student postItem(@RequestBody Student student){
        seq++;
        Long id = seq;
        student.setId(id);
        studentMap.put(id, student);
        return student;
    }

    //  Zápis změn údajů o studentovi do mapy ---- PUT ()
    @GetMapping ("/studentsTestAdd")
    public Student putItem(){
        // Vytvoření nového studenta (nového objektu)
        Student student = new Student(1234L, "František", "Tůma", 61);
        // Přídání do kolekce
        studentMap.put(1234L, student);
        return student;
    }


/*
GET	    /students	    Prečtení informací o všech studentech (celé kolekce)
GET	    /students/123	Přečtení stavu studenta (objektu s id 123)
POST    /students	    Vytvoření nového studenta (nového objektu)
PUT	    /students/123	Update stavu studenta (objektu s id 123)
DELETE  /students/123	Odstranění studenta (objektu s id 123)
 */

    // Prečtení informací o všech studentech (celé kolekce) ---- GET
    @GetMapping ("/students")
    public Collection<Student> getAllItems(){
        return studentMap.values();
    }

    // Přečtení stavu studenta (objektu s id 123) ---- GET
    @GetMapping("/students/{id}")
    public Student getItemById(@PathVariable("id") Long id) throws Exception {
        if (!studentMap.containsKey(id)) {
            // Výstup na konzoli
            System.out.println  ("Klíč:"+ id + " v kolekci studentů neexistuje !");
            // Vyvolání výjímky
            throw new Exception ("Klíč:" + id + " v kolekci studentů nexistuje !");
        }
        return studentMap.get(id);
    }

    // Přidání studenta do mapy (objekt s id 123) ---- POST
    @PostMapping("/students/{id}")
    public Student postItemById(@PathVariable("id") Long id,@RequestBody Student student) throws Exception {
        if (!studentMap.containsKey(id)) {
            studentMap.putIfAbsent(id, student);
        }
        else {
            // Výstup na konzoli
            System.out.println  ("Klíč:       "+ id +" v kolekci studentů již existuje !");
//            Student tempStudent = new Student(0L, "","",0);
            Student tempStudent;
            tempStudent = studentMap.get(id);
            System.out.println  (" - Jmeno:   " + tempStudent.getJmeno()+ "\n"+
                                 " - Príjmeni:" + tempStudent.getPrijmeni()+ "\n"+
                                 " - Vek:     " + tempStudent.getVek()
                                );
            // Vyvolání výjímky
            throw new Exception("Klíč: " + id + " v kolekci studentů již existuje !");
        }
        return student;
    }

    // 	Update stavu studenta (objektu s id 123) ---- PUT
    @PutMapping("/students/{id}")
    public Student putItemById(@PathVariable("id") Long id, @RequestBody Student student){
        studentMap.put(id, student);
        return student;
    }

    // Odstranění studenta (objektu s id 123) z kolekce ---- DELETE
    @DeleteMapping("/students/{id}")
    public void deleteItemById(@PathVariable("id") Long id){
        studentMap.remove(id);
    }

    // Chybové stavy
    //    - vygenerování vyjímky
    @GetMapping("/studentsError")
    public Student testError(@RequestParam(value = "hodnota", required = false) String hodnota) throws Exception {
        throw new Exception("Chyba " + hodnota + " !");
    }

    // Chybové stavy
    //     - obsluha vyjímek
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        e.printStackTrace();
        return new ErrorResponse(e.getMessage(), e.getStackTrace(), LocalDateTime.now());
    }
}